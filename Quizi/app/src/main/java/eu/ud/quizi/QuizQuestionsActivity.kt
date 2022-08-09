package eu.ud.quizi

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import eu.ResultActivity


class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int =1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOptionPosition: Int =0
    private var mUserName: String?= null
    private var mCorrectAnswer:Int=0
    private var progressBar: ProgressBar?= null
    private var tvQuestion: TextView?= null
    private var tvProgress: TextView?= null
    private var ivImage: ImageView?= null

    private var tvOptionOne: TextView?= null
    private var tvOptionTwo: TextView?= null
    private var tvOptionThree: TextView?= null
    private var tvOptionFour: TextView?= null
    private var btnSubmit: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName=intent.getStringExtra(Constants.USER_NAME)
        progressBar = findViewById(R.id.progress_bar)
        tvQuestion= findViewById(R.id.tvQuestion)
        tvProgress= findViewById(R.id.tv_progress)
        ivImage= findViewById(R.id.iv_image)

        tvOptionOne= findViewById(R.id.tv_option_one)
        tvOptionTwo= findViewById(R.id.tv_option_Two)
        tvOptionThree= findViewById(R.id.tv_option_Three)
        tvOptionFour= findViewById(R.id.tv_option_Four)
        btnSubmit=findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestion()
        setQuestion()
        defaultOptionsView()
    }

    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        ivImage?.setImageResource(question.image)
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.OptionOne
        tvOptionTwo?.text = question.OptionTwo
        tvOptionThree?.text = question.OptionThree
        tvOptionFour?.text = question.OptionFour
        if(mCurrentPosition==mQuestionList!!.size){
            btnSubmit?.text="FINISH"
        }else{
            btnSubmit?.text="SUBMIT"
        }
    }
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0,it)
        }
        tvOptionTwo?.let {
            options.add(1,it)
        }
        tvOptionThree?.let {
            options.add(2,it)
        }
        tvOptionFour?.let {
            options.add(3,it)
            for (option in options){
                option.setTextColor(Color.parseColor("#7A8089"))
                option.typeface= Typeface.DEFAULT
                option.background=ContextCompat.getDrawable(
                    this,
                    R.drawable.defacult_option_border_bg

                )
            }
        }
    }
    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition= selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )


    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one-> {
                tvOptionOne?.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.tv_option_Two-> {
                tvOptionTwo?.let {
                    selectedOptionView(it,2)
                }
            }
            R.id.tv_option_Three-> {
                tvOptionThree?.let {
                    selectedOptionView(it,3)
                }
            }
            R.id.tv_option_Four-> {
                tvOptionFour?.let {
                    selectedOptionView(it,4)
                }

            }
            R.id.btn_submit->{
                if (mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition<= mQuestionList!!.size->{
                            setQuestion()
                        }
                        else->{
                            val intent =Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question=mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer !=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition==mQuestionList!!.size){
                        btnSubmit?.text ="FINISH"
                    }else{
                        btnSubmit?.text="Go To Next Qestion"
                    }
                    mSelectedOptionPosition=0
                }
            }
        }
    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                tvOptionOne?.background=ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2->{
                tvOptionTwo?.background=ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3->{
                tvOptionThree?.background=ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4->{
                tvOptionFour?.background=ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}