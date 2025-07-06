package com.nayab.contactmnager

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewContactFragment(private val onDismissCallback: () -> Unit) : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firstNameInput = view.findViewById<EditText>(R.id.etFirstName)
        val lastNameInput = view.findViewById<EditText>(R.id.etLastName)
        val phoneInput = view.findViewById<EditText>(R.id.etPhone)
        val saveButton = view.findViewById<View>(R.id.btnSave)

        saveButton.setOnClickListener {
            val first = firstNameInput.text.toString().trim()
            val last = lastNameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()

            if (first.isEmpty() || last.isEmpty() || phone.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // 1) add to in‑memory list
                ContactData.contactList.add(Contact(first, last, phone))
                // 2) notify MainActivity (which will save and refresh)
                onDismissCallback()
                // 3) then dismiss the sheet
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as? BottomSheetDialog
        val bottomSheet =
            dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissCallback()
    }
}
