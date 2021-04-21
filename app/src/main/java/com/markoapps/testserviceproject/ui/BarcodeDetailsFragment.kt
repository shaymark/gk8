package com.markoapps.testserviceproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.markoapps.testserviceproject.R
import kotlinx.android.synthetic.main.fragment_barcode_details.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BarcodeDetailsFragment : Fragment() {

    val args: BarcodeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barcode_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barcodeDetails = args.barcode
        refreshUi(barcodeDetails)

    }

    private fun refreshUi(barcodeDetails: String) {
        barcode.text = barcodeDetails
    }
}