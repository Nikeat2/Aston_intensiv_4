package com.example.astonhomework4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

const val USER_NAME = "USER_NAME"
const val USER_SURNAME = "USER_SURNAME"
const val USER_PHONE_NUMBER = "USER_PHONE_NUMBER"
const val USER_IMAGE = "USER_IMAGE"
const val USER_POSITION = "USER_POSITION"

class UserDetailsFragment : Fragment() {
    private var userName: String? = null
    private var userSurname: String? = null
    private var userPhoneNumber: String? = null
    private var userImage: String? = null
    private lateinit var etDetailedUserName: EditText
    private lateinit var etDetailedUserSurname: EditText
    private lateinit var etDetailedUserPhoneNumber: EditText
    private lateinit var ivDetailedUserImage: ImageView
    private lateinit var btnSaveChanges: Button
    private lateinit var changedName: String
    private lateinit var changedSurname: String
    private lateinit var changedPhoneNumber: String
    private var userPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString(USER_NAME)
            userSurname = it.getString(USER_SURNAME)
            userPhoneNumber = it.getString(USER_PHONE_NUMBER)
            userImage = it.getString(USER_IMAGE)
            userPosition = it.getInt(USER_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_details, container, false)
        etDetailedUserName = view.findViewById(R.id.etDetailedUserName)
        etDetailedUserSurname = view.findViewById(R.id.etDetailedUserSurname)
        etDetailedUserPhoneNumber = view.findViewById(R.id.etDetailedUserPhoneNumber)
        ivDetailedUserImage = view.findViewById(R.id.ivDetailedUser)
        btnSaveChanges = view.findViewById(R.id.btnSaveChanges)

        if (savedInstanceState != null) {
            userName = savedInstanceState.getString(USER_NAME)
            userSurname = savedInstanceState.getString(USER_SURNAME)
            userPhoneNumber = savedInstanceState.getString(USER_PHONE_NUMBER)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etDetailedUserName.setText(userName)
        etDetailedUserSurname.setText(userSurname)
        etDetailedUserPhoneNumber.setText(userPhoneNumber)
        Glide.with(this).load(userImage).centerCrop().centerInside().into(ivDetailedUserImage)
        btnSaveChanges.setOnClickListener {
            setFragmentResult()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USER_NAME, etDetailedUserName.text.toString())
        outState.putString(USER_SURNAME, etDetailedUserSurname.text.toString())
        outState.putString(USER_PHONE_NUMBER, etDetailedUserPhoneNumber.text.toString())
    }

    private fun setFragmentResult() {
        changedName = etDetailedUserName.text.toString()
        changedSurname = etDetailedUserSurname.text.toString()
        changedPhoneNumber = etDetailedUserPhoneNumber.text.toString()

        val changedUserInfo = bundleOf(
            CHANGED_USER_NAME to changedName,
            CHANGED_USER_SURNAME to changedSurname,
            CHANGED_USER_PHONE_NUMBER to changedPhoneNumber,
            USER_POSITION to userPosition
        )

        val changedUser = Bundle().apply {
            putBundle(CHANGED_USER_INFO, changedUserInfo)
        }
        parentFragmentManager.setFragmentResult(CHANGED_USER_INFO, changedUser)
        parentFragmentManager.popBackStack(USERS_LIST_FRAGMENT, 1)

    }

    companion object {
        @JvmStatic
        fun newInstance(user: Users) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(USER_SURNAME, user.surname)
                putString(USER_NAME, user.name)
                putString(USER_PHONE_NUMBER, user.phoneNumber)
                putString(USER_IMAGE, user.image)
                putInt(USER_POSITION, user.id - 1)
            }
        }
    }
}



