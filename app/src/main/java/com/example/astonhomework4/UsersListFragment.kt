package com.example.astonhomework4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UsersListFragment : Fragment() {
    private lateinit var rvUsers: RecyclerView
    private lateinit var adapter: UsersAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var userDetailsFragment: UserDetailsFragment
    private lateinit var usersList: MutableList<Users>
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)
        rvUsers = view.findViewById(R.id.rvUsers)
        usersList = mutableListOf()
        usersList.addAll(MockUsers.mockUsers)
        adapter = UsersAdapter(usersList)
        layoutManager = LinearLayoutManager(requireContext())
        rvUsers.adapter = adapter
        rvUsers.layoutManager = layoutManager

        savedInstanceState?.let {
            val usersJson = it.getString("usersListJson")
            usersList = if (usersJson != null) {
                gson.fromJson(usersJson, object : TypeToken<List<Users>>() {}.type)
            } else {
                mutableListOf()
            }
            adapter.updateList(usersList.toList())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener()
        adapter.setOnClickListener(object : UsersAdapter.OnUserClickListener {
            override fun onUserClick(position: Int) {
                val userDetailed = usersList[position]
                getToDetailsFragment(userDetailed)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val usersJson = gson.toJson(usersList)
        outState.putString("usersListJson", usersJson)
    }

    private fun getToDetailsFragment(user: Users) {
        userDetailsFragment = UserDetailsFragment.newInstance(user)
        parentFragmentManager.beginTransaction().replace(R.id.main, userDetailsFragment)
            .addToBackStack(
                USERS_LIST_FRAGMENT
            ).commit()
    }

    private fun setFragmentResultListener() {
        parentFragmentManager.setFragmentResultListener(
            CHANGED_USER_INFO, viewLifecycleOwner
        ) { _, bundle ->
            val changedUserInfo = bundle.getBundle(CHANGED_USER_INFO)
            val changedUserName = changedUserInfo?.getString(CHANGED_USER_NAME)
            val changedUserSurname = changedUserInfo?.getString(CHANGED_USER_SURNAME)
            val changedUserPhoneNumber = changedUserInfo?.getString(CHANGED_USER_PHONE_NUMBER)
            val changedUserPosition = changedUserInfo!!.getInt(USER_POSITION)

            val newList = mutableListOf<Users>()
            newList.addAll(usersList)
            if (changedUserPosition in usersList.indices) {
                val userCopy = usersList[changedUserPosition].copy(
                    name = changedUserName.toString(),
                    surname = changedUserSurname.toString(),
                    phoneNumber = changedUserPhoneNumber.toString()
                )
                newList.removeAt(changedUserPosition)
                newList.add(changedUserPosition, userCopy)
            }
            adapter.updateList(newList.toList())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsersListFragment()
    }
}

const val CHANGED_USER_INFO = "CHANGED_USER<INFO"
const val USERS_LIST_FRAGMENT = "USERS_LIST_FRAGMENT"
const val CHANGED_USER_NAME = "CHANGED_USER_NAME"
const val CHANGED_USER_SURNAME = "CHANGED_USER_SURNAME"
const val CHANGED_USER_PHONE_NUMBER = "CHANGED_USER_PHONE_NUMBER"