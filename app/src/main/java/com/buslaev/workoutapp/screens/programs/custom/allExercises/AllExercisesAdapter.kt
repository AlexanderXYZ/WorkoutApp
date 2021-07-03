package com.buslaev.workoutapp.screens.programs.custom.allExercises

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.screens.exercises.ExerciseData

class AllExercisesAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AllExercisesAdapter.AllExercisesViewHolder>() {

    interface OnItemClickListener {
        fun onClick(position: Int, id: String, title: String, imageUrl: String)
    }

    inner class AllExercisesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val title: TextView = itemView.findViewById(R.id.title_muscle_item)
        val image: ImageView = itemView.findViewById(R.id.image_muscle_item)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            val currentView = mList[position]
            p0?.setBackgroundColor(Color.RED)
            if (position != RecyclerView.NO_POSITION) {
                currentView.imageUrl?.let {
                    listener.onClick(
                        position,
                        currentView.id,
                        currentView.title,
                        it
                    )
                }
            }
        }

    }

    private var mList = emptyList<ExerciseData>()

    fun setList(list: List<ExerciseData>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllExercisesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.muscle_item, parent, false)
        return AllExercisesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllExercisesViewHolder, position: Int) {
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
}