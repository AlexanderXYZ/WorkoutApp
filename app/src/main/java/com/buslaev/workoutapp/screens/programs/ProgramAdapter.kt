package com.buslaev.workoutapp.screens.programs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.roomData.OwnProgramData

class ProgramAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    inner class ProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val title: TextView = itemView.findViewById(R.id.title_muscle_item)
        val image: ImageView = itemView.findViewById(R.id.image_muscle_item)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            val currentView = mList[position]
            if (position != RecyclerView.NO_POSITION) {
                listener.onClick(
                    position,
                    currentView.title,
                    currentView.imageUrl,
                    currentView.title
                )
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(position: Int, title: String, imageUrl: String, description: String)
    }

    private var mList = emptyList<OwnProgramData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.muscle_item, parent, false)
        return ProgramViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val currentPosition = mList[position]
        holder.title.text = currentPosition.title
        Glide
            .with(context)
            .load(currentPosition.imageUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(list: List<OwnProgramData>) {
        mList = list
        notifyDataSetChanged()
    }
}