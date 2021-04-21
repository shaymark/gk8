package com.markoapps.testserviceproject.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.markoapps.testserviceproject.R
import com.markoapps.testserviceproject.core.Status
import com.markoapps.testserviceproject.di.Provider
import com.markoapps.testserviceproject.utils.Constans
import com.markoapps.testserviceproject.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    val viewModel: LoginViewModel by viewModels { Provider.loginViewModelFactory}
    val encryptionUtil = Provider.encryptionUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_btn.setOnClickListener {
            viewModel.login(
                userName = username_input_layout.editText!!.text.toString(),
                password = password_input_layout.editText!!.text.toString())
                .observe(viewLifecycleOwner) {  result ->

                    login_btn.isEnabled = result.status != Status.LOADING

                    when(result.status) {
                        Status.SUCCESS -> {openBarCodeScanner()}
                        Status.ERROR -> {
                            if(result.message == "HTTP 401") {
                                showError("invalid username or password, please try again")
                            } else {
                                showError("oops something went wrong ...")
                            }
                        }}
                }
        }

        scan_btn.setOnClickListener {
            openBarCodeScanner()
        }
    }

    fun onGetBarcodeString(barcodeText: String) {
        barcodeText?.let {
            decodeBarcode(barcodeText = it)?.let { decodedString ->
                val action = LoginFragmentDirections.actionLoginFragmentToBarcodeDetailsFragment(decodedString)
                findNavController().navigate(action)
            } ?: run {
                showError("unable to parse the barcode")
            }
        }

    }

    private fun decodeBarcode(barcodeText: String): String? {
        return encryptionUtil.decryptECB256(Constans.secretKeySpec, barcodeText)
    }

    private fun openBarCodeScanner(){
        runOnCameraPermission = {
            val intent = Intent(context, SimpleScannerActivity::class.java)
            resultLauncher.launch(intent)
        }
        checkForCameraPermissions ()
    }

    private fun showError(errorText: String) {
        MaterialDialog(requireContext()).show {
            title(text = errorText)
            lifecycleOwner(viewLifecycleOwner)
        }
    }

    var runOnCameraPermission : () -> Unit = {}

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            result?.data?.getStringExtra("value")?.let {
                onGetBarcodeString(it)
            }
        } else {
            showError("unable to scan barcode")
        }
    }

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher. You can use either a val, as shown in this snippet,
    // or a lateinit var in your onAttach() or onCreate() method.
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                runOnCameraPermission()
                runOnCameraPermission = {}
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                MaterialDialog(requireContext()).show {
                    title(text = "can not scan barcode because you didn't give camera permissions")
                    lifecycleOwner(viewLifecycleOwner)
                }
            }
        }

    fun checkForCameraPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                runOnCameraPermission()
                runOnCameraPermission = {}
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
                MaterialDialog(requireContext()).show {
                    title(text = "please give camera permission to use the barcode functionality")
                    lifecycleOwner(viewLifecycleOwner)
                }
        }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA)
            }
        }
    }
}