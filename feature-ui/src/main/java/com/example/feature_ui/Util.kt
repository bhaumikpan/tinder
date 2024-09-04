package com.example.feature_ui

object Util {
    /*○ “a few minutes ago” for >= 2 minutes but < 15 minutes ago
    ○ “X minutes ago” for >= 15 minutes
    ○ “1hourago”for>=1hourbut<2hours
    ○ “X hour(s) ago” for >= 2 hours
    ○ “1dayago”for>=1daybut<2days
    ○ “X day(s) ago” for >= 2 days*/

   /* getTimeAgoString(duration: Duration): String {
        val minutes = duration.toMinutes()
        val hours = duration.toHours()
        val days = duration.toDays()

        return when {
            minutes < 2 -> "Just now"
            minutes in 2 until 15 -> "a few minutes ago"
            minutes in 15 until 60 -> "$minutes minutes ago"
            hours == 1L -> "1 hour ago"
            hours in 2 until 24 -> "$hours hours ago"
            days == 1L -> "1 day ago"
            else -> "$days days ago"
        }
    }*/

}