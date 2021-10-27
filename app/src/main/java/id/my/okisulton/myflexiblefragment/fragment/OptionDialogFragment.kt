package id.my.okisulton.myflexiblefragment.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import id.my.okisulton.myflexiblefragment.R
import id.my.okisulton.myflexiblefragment.databinding.FragmentOptionDialogBinding

class OptionDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentOptionDialogBinding

    private var optionDialogListener: OnOptionDialogListener? = null

    interface OnOptionDialogListener {
        fun onOptionChoosen(text: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOptionDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChoose.setOnClickListener {
            val checkedRadioButtonId = binding.rgOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var coach: String? = null
                when (checkedRadioButtonId) {
                    R.id.rb_saf -> coach = binding.rbSaf.text.toString()
                    R.id.rb_mou -> coach = binding.rbMou.text.toString()
                    R.id.rb_lvg -> coach = binding.rbLvg.text.toString()
                    R.id.rb_moyes -> coach = binding.rbMoyes.text.toString()
                }
                optionDialogListener?.onOptionChoosen(coach)
                dialog?.dismiss()
            }
        }

        binding.btnClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment
        if (fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }
}