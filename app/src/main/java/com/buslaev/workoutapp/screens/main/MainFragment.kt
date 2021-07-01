package com.buslaev.workoutapp.screens.main

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.CalendarDayLayoutBinding
import com.buslaev.workoutapp.databinding.CalendarMonthHeaderLayoutBinding
import com.buslaev.workoutapp.databinding.FragmentMainBinding
import com.buslaev.workoutapp.utilits.APP_ACTIVITY
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.*


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    private var selectedDate: LocalDate? = null
    private var selectedDays:List<LocalDate> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        initCalendar()
    }

    private fun initCalendar() {

        //Day Container
        class DayViewContainer(view: View) : ViewContainer(view) {
            val calendarDay = CalendarDayLayoutBinding.bind(view).calendarDayText

            lateinit var day: CalendarDay

            init {
                view.setOnClickListener {
                    Toast.makeText(
                        APP_ACTIVITY,
                        "selected ${day.date.dayOfWeek}",
                        Toast.LENGTH_SHORT
                    ).show()

                    if (day.owner == DayOwner.THIS_MONTH) {
                        // Keep a reference to any previous selection
                        // in case we overwrite it and need to reload it.
                        val currentSelection = selectedDate
                        if (currentSelection == day.date) {
                            // If the user clicks the same date, clear selection.
                            selectedDate = null
                            // Reload this date so the dayBinder is called
                            // and we can REMOVE the selection background.
                            mBinding.calendarView.notifyDateChanged(currentSelection)
                        } else {
                            selectedDate = day.date
                            // Reload the newly selected date so the dayBinder is
                            // called and we can ADD the selection background.
                            mBinding.calendarView.notifyDateChanged(day.date)
//                            if (currentSelection != null) {
//                                // We need to also reload the previously selected
//                                // date so we can REMOVE the selection background.
//                                mBinding.calendarView.notifyDateChanged(currentSelection)
//                            }
                        }
                    }
                }
            }
        }

        mBinding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, day: CalendarDay) {

                container.day = day
                val calendarDay = container.calendarDay
                calendarDay.text = day.date.dayOfMonth.toString()
                if (day.owner == DayOwner.THIS_MONTH) {
                    // Show the month dates. Remember that views are recycled!
                    container.calendarDay.setTextColor(Color.BLACK)

                    if (day.date == selectedDate) {
                        // If this is the selected date, show a round background and change the text color.
                        calendarDay.setTextColor(Color.WHITE)
                        calendarDay.setBackgroundResource(R.drawable.background_muscle_item)
                    } else {
                        // If this is NOT the selected date, remove the background and reset the text color.
                        calendarDay.setTextColor(Color.BLACK)
                        calendarDay.background = null
                    }
                } else {
                    // Hide in and out dates
                    container.calendarDay.setTextColor(Color.GRAY)
                }
            }
        }

        //Month Container
        class MonthViewContainer(view: View) : ViewContainer(view) {
            val monthDate = CalendarMonthHeaderLayoutBinding.bind(view).headerTextView
        }

        mBinding.calendarView.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                    container.monthDate.text =
                        "${month.yearMonth.month.name.toLowerCase().capitalize()} ${month.year}"
                }
            }

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(10)
        val lastMonth = currentMonth.plusMonths(10)
        val daysOfWeek = arrayOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
        )
        mBinding.calendarView.setup(firstMonth, lastMonth, daysOfWeek.first())
        mBinding.calendarView.scrollToMonth(currentMonth)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu -> {
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
            }
            else -> true
        }
        return super.onOptionsItemSelected(item)
    }
}