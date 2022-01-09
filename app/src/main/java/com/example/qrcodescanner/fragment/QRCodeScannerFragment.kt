package com.example.qrcodescanner.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.example.qrcodescanner.R
import com.example.qrcodescanner.databinding.FragmentQrCodeScannerBinding

class QRCodeScannerFragment : Fragment() {
    private lateinit var binding: FragmentQrCodeScannerBinding

    private lateinit var codeScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_qr_code_scanner, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, binding.scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {


            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }


    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()

    }

}