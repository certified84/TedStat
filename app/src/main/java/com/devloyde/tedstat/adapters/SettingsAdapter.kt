package com.devloyde.tedstat.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.devloyde.tedstat.databinding.FragmentSettingsItemBinding
import com.devloyde.tedstat.db.SharedPref
import com.devloyde.tedstat.listeners.NavigationListeners
import com.devloyde.tedstat.models.SettingsListItem
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.fragment_settings_item.view.*

class SettingsAdapter(
    private val sharedPref: SharedPref,
    private val mItems: List<SettingsListItem>,
    private val mListener: NavigationListeners.SettingsNavigationListener?
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    private val shareAppPosition: Int = 0
    private val openSourceLicensesPosition: Int = 1
    private val creditsPosition: Int = 2
    private val aboutUsPosition: Int = 3
    private val feedbackPosition: Int = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentSettingsItemBinding.inflate(inflater, parent, false)
        return SettingsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = mItems[position]

        holder.mIconView.setImageResource(item.icon)
        holder.mTitleView.text = item.title
        if (item.pref === null) {
            holder.mPref.visibility = View.INVISIBLE
        } else {
            holder.mPref.visibility = View.VISIBLE
            holder.mPref.isChecked = sharedPref.loadDarkModeState()
            holder.mPref.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    mListener?.restartApp(true)
                } else {
                    mListener?.restartApp(false)
                }
            }
        }

        with(holder.mItemView) {
            tag = item
        }

        holder.itemView.setOnClickListener {
            when (position) {
                shareAppPosition -> {
                    mListener?.launchShare()
                }
                openSourceLicensesPosition -> {
                    mListener?.launchOpenSourceLicenses()
                }
                creditsPosition -> {
                    mListener?.launchCredits()
                }
                aboutUsPosition -> {
                    mListener?.launchAbout()
                }
                feedbackPosition -> {
                    mListener?.launchFeedBack()
                }
            }
        }
    }

    override fun getItemCount(): Int = mItems.size

    inner class SettingsViewHolder(val mItemView: View) : RecyclerView.ViewHolder(mItemView) {
        val mIconView: ImageView = mItemView.settings_icon
        val mTitleView: TextView = mItemView.settings_title
        val mPref: SwitchMaterial = mItemView.settings_pref

    }
}
