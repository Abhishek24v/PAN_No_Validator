package com.epifiapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.epifiapp.databinding.FragmentGetDetailsBinding
import com.epifiapp.utils.*

class GetDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGetDetailsBinding
    private val viewModel by activityViewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            nextButton.isButtonEnable(false)
            panInput.setText(viewModel.getPAN())
            dobDateInput.setText(viewModel.getDate())
            dobMonthInput.setText(viewModel.getMonth())
            dobYearInput.setText(viewModel.getYear())
        }
    }

    private fun setClicks() {
        binding.apply {
            panInput.afterTextChanged {
                if (it.length == 10) dobDateInput.requestFocus()
                nextButton.isButtonEnable(validateEntries(panInput.text.toString(), getDob()))
                viewModel.updatePAN(panInput.text.toString())
            }
            dobDateInput.afterTextChanged {
                if (it.length == 2) dobMonthInput.requestFocus()
                nextButton.isButtonEnable(validateEntries(panInput.text.toString(), getDob()))
                viewModel.updateDate(dobDateInput.text.toString())
            }
            dobMonthInput.afterTextChanged {
                if (it.length == 2) dobYearInput.requestFocus()
                nextButton.isButtonEnable(validateEntries(panInput.text.toString(), getDob()))
                viewModel.updateMonth(dobMonthInput.text.toString())
            }
            dobYearInput.afterTextChanged {
                if (it.length == 4) activity?.hideKeyboard(dobYearInput)
                nextButton.isButtonEnable(validateEntries(panInput.text.toString(), getDob()))
                viewModel.updateYear(dobYearInput.text.toString())
            }

            nextButton.setOnClickListener {
                activity?.let { it -> viewModel.addData(it) }
                Toast.makeText(activity, "Details submitted successfully", Toast.LENGTH_SHORT)
                    .show()
                activity?.finish()
            }

            dontHavePan.setOnClickListener {
                activity?.finish()
            }
        }
    }

    private fun getDob(): String {
        return binding.dobDateInput.text.toString() + "-" + binding.dobMonthInput.text.toString() + "-" + binding.dobYearInput.text.toString()
    }

    override fun onResume() {
        super.onResume()
        setClicks()
    }

    private fun validateEntries(panNo: String, dob: String): Boolean {
        return panNo.isValidPan() && dob.isValidDob()
    }

}