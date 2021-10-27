package id.my.okisulton.myflexiblefragment.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.my.okisulton.myflexiblefragment.ProfilActivity
import id.my.okisulton.myflexiblefragment.R
import id.my.okisulton.myflexiblefragment.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment() {

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    private lateinit var binding: FragmentDetailCategoryBinding

    var description: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }

        if (arguments != null) {
            val categoryName = arguments?.getString(EXTRA_NAME)
            binding.tvCategoryName.text = categoryName
            binding.tvCategoryDescription.text = description
        }

        binding.btnShowDialog.setOnClickListener {
            val mOptionDialogFragment = OptionDialogFragment()

            val mFragmentManager = childFragmentManager
            mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(activity, ProfilActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_DESCRIPTION, description)
    }

    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChoosen(text: String?) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }
}