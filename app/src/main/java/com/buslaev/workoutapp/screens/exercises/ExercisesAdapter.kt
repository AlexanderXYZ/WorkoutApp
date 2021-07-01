package com.buslaev.workoutapp.screens.exercises

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.buslaev.workoutapp.R

class ExercisesAdapter(
    private val listener: OnItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder>() {

    inner class ExercisesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val title: TextView = itemView.findViewById(R.id.title_muscle_item)
        val image: ImageView = itemView.findViewById(R.id.image_muscle_item)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            val exercise = mList[position].title
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position,exercise)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int,exercise:String)
    }

    private var mList = emptyList<ExerciseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.muscle_item, parent, false)
        return ExercisesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
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

    fun setList(list: List<ExerciseData>) {
        mList = list
        notifyDataSetChanged()
    }
}