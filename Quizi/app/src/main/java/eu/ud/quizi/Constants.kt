package eu.ud.quizi

object Constants {

    const val USER_NAME:String="user_name"
    const val TOTAL_QUESTIONS:String="total_questions"
    const val CORRECT_ANSWERS:String="correct_answers"

    fun getQuestion(): ArrayList<Question>{
        val questionList= ArrayList<Question>()


        val ques1 = Question(1,"What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina","India","Australia","Fizi",
        1
        )
        questionList.add(ques1)

        val ques2 = Question(2,"What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "belgium","India","Australia","Fizi",
        3
        )
        questionList.add(ques2)

        val ques3 = Question(3,"What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Brazil","Pakistan","Austria","Belgium",
        4
        )
        questionList.add(ques3)

        val ques4 = Question(4,"What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Argentina","Brazil","Australia","Fiji",
        2
        )
        questionList.add(ques4)

        val ques5 = Question(5,"What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "kuwait","Russia","Denmark","India",
        3
        )
        questionList.add(ques5)

        val ques6 = Question(6,"What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Argentina","India","Australia","Fiji",
        4
        )
        questionList.add(ques6)

        val ques7 = Question(7,"What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Germany","Saudi Arabia","Australia","New Zealand",
        1
        )
        questionList.add(ques7)

        val ques8 = Question(8,"What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "India","Denmark","China","Sri Lanka",
        1
        )
        questionList.add(ques8)

        val ques9 = Question(9,"What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "England","Hungary","Kuwait","Fiji",
        3
        )
        questionList.add(ques9)

        val ques10 = Question(10,"What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Argentina","New Zealand","Australia","Fiji",
        2
        )
        questionList.add(ques10)


         return questionList
    }
}