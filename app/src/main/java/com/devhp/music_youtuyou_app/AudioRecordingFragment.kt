package com.devhp.music_youtuyou_app

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devhp.music_youtuyou_app.databinding.FragmentAudoRecordingBinding
import com.devhp.music_youtuyou_app.presentation.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


class AudioRecordingFragment : Fragment() {

    private lateinit var binding: FragmentAudoRecordingBinding


    private var bufferSize: Int = 0
    private lateinit var micFile: File
    private lateinit var speakerFile: File
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAudoRecordingBinding.inflate(inflater, container, false)
        // Khởi tạo các biến cần thiết
        val sampleRate = 44100 // Tần số lấy mẫu
        val channelConfig = AudioFormat.CHANNEL_IN_STEREO // Cấu hình kênh âm thanh
        val audioFormat = AudioFormat.ENCODING_PCM_16BIT // Định dạng âm thanh
        bufferSize = AudioRecord.getMinBufferSize(
            sampleRate,
            channelConfig,
            audioFormat
        ) // Kích thước bộ đệm
        val micSource = MediaRecorder.AudioSource.MIC // Nguồn âm thanh từ mic
        val speakerSource =
            MediaRecorder.AudioSource.VOICE_COMMUNICATION // Nguồn âm thanh từ loa

        // Tạo hai đối tượng AudioRecord cho mic và loa
        @SuppressLint("MissingPermission")
        val micRecorder = AudioRecord(micSource, sampleRate, channelConfig, audioFormat, bufferSize)

        @SuppressLint("MissingPermission")
        val speakerRecorder =
            AudioRecord(speakerSource, sampleRate, channelConfig, audioFormat, bufferSize)

        // Tạo hai file để lưu dữ liệu âm thanh thô từ mic và loa
        micFile = File(requireActivity().getExternalFilesDir(null), "mic.pcm")
        speakerFile = File(requireActivity().getExternalFilesDir(null), "speaker.pcm")

        // Tạo hai luồng xuất để ghi dữ liệu vào file
        val micOutputStream = FileOutputStream(micFile)
        val speakerOutputStream = FileOutputStream(speakerFile)

        // Tạo một biến boolean để kiểm tra trạng thái ghi âm
        var isRecording = true

        binding.startRecord.setOnClickListener {
            // Bắt đầu ghi âm
            micRecorder.startRecording()
            speakerRecorder.startRecording()

// Tạo một coroutine scope để chạy các coroutine bất đồng bộ
            CoroutineScope(Dispatchers.Main).launch {
                // Tạo hai coroutine async để ghi âm từ mic và loa
                val micJob = async {
                    withContext(Dispatchers.IO) {
                        while (isRecording) {
                            recordAudio(micRecorder, micOutputStream)
                        }
                        stopRecording(micRecorder, micOutputStream)
                    }
                }
                val speakerJob = async {
                    withContext(Dispatchers.IO) {
                        while (isRecording) {
                            recordAudio(speakerRecorder, speakerOutputStream)
                        }
                        stopRecording(speakerRecorder, speakerOutputStream)
                    }
                }
                // Đợi kết quả trả về từ hai coroutine
                micJob.await()
                speakerJob.await()
            }
        }

        binding.stopRecord.setOnClickListener {
            isRecording = false
        }

        binding.getFilePath.setOnClickListener {
            Log.d(MainActivity.TAG, "MicFile: ${micFile.absolutePath}")
            Log.d(MainActivity.TAG, "SpeakerFile: ${speakerFile.absolutePath}")
        }
        return binding.root
    }

    // Tạo một hàm suspend để ghi âm liên tục trong khi isRecording là true
    private suspend fun recordAudio(recorder: AudioRecord, outputStream: FileOutputStream) {
        // Tạo một mảng byte để lưu dữ liệu đọc được từ bộ đệm
        val data = ByteArray(bufferSize)
        // Đọc dữ liệu từ bộ đệm vào mảng byte
        val read = recorder.read(data, 0, bufferSize)
        // Kiểm tra xem có lỗi nào xảy ra khi đọc dữ liệu không
        if (read != AudioRecord.ERROR_INVALID_OPERATION) {
            // Ghi dữ liệu vào file
            outputStream.write(data)
        }
    }

    // Tạo một hàm suspend để đóng luồng xuất và giải phóng tài nguyên khi kết thúc ghi âm
    private suspend fun stopRecording(recorder: AudioRecord, outputStream: FileOutputStream) {
        // Đóng luồng xuất và giải phóng tài nguyên
        outputStream.close()
        recorder.stop()
        recorder.release()

    }


}