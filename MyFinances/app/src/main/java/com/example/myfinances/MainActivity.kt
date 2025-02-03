package com.example.myfinances

import android.os.Bundle
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private lateinit var selectProductRadioGroup: RadioGroup
    private lateinit var cdRadioButton: RadioButton
    private lateinit var loanRadioButton: RadioButton
    private lateinit var checkingAccountRadioButton: RadioButton
    private lateinit var accountNumberTextView: TextView
    private lateinit var accountNumberEditText: EditText
    private lateinit var initialBalanceTextView: TextView
    private lateinit var initialBalanceEditText: EditText
    private lateinit var currentBalanceTextView: TextView
    private lateinit var currentBalanceEditText: EditText
    private lateinit var paymentAmountTextView: TextView
    private lateinit var paymentAmountEditText: EditText
    private lateinit var interestRateTextView: TextView
    private lateinit var interestRateEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var currentFinance: Finance
    private lateinit var messageTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get References from Layout
        selectProductRadioGroup = findViewById(R.id.radioGroupSelectProduct)
        cdRadioButton = findViewById(R.id.radioButtonCd)
        loanRadioButton = findViewById(R.id.radioButtonLoan)
        checkingAccountRadioButton = findViewById(R.id.radioButtonCheckingAccount)
        accountNumberEditText = findViewById(R.id.editTextAccountNumber)
        initialBalanceEditText = findViewById(R.id.editTextInitialBalance)
        currentBalanceEditText = findViewById(R.id.editTextCurrentBalance)
        paymentAmountEditText = findViewById(R.id.editTextPaymentAmount)
        interestRateEditText = findViewById(R.id.editTextInterestRate)
        saveButton = findViewById(R.id.buttonSave)
        cancelButton = findViewById(R.id.buttonCancel)
        accountNumberTextView = findViewById(R.id.textViewAccountNumber)
        initialBalanceTextView = findViewById(R.id.textViewInitialBalance)
        currentBalanceTextView = findViewById(R.id.textViewCurrentBalance)
        paymentAmountTextView = findViewById(R.id.textViewPaymentAmount)
        interestRateTextView = findViewById(R.id.textViewInterestRate)
        messageTextView = findViewById(R.id.textViewDisplayMessage)


        // function to disable all Details Section until product is select-- do it initially
        initResetScreen()
        initRadioButtonCd()
        initRadioButtonLoan()
        initRadioButtonCheckingAccount()
        initButtonCancel()
        initButtonSave()
        initTextChangedEvents()
        clearMessage()
        currentFinance = Finance()


    }  // End Of On Create Function
    // function to enable or disable Edit text fields

    private fun initResetScreen() {
        selectProductRadioGroup.clearCheck()
        clearFinanceData()
        initMainScreen(
            enAccountNumber = false,
            enInitialAmt = false,
            enCurrentAmt = false,
            enPayAmt = false,
            enIntRate = false
        )
        //        clearMessage()
    } // End of initResetScreen


    private fun initRadioButtonCd() {
        // get listener for CD
        cdRadioButton.setOnClickListener {
            clearMessage()
            currentFinance.accounttype = "CD"
            initMainScreen(
                enAccountNumber = true,
                enInitialAmt = true,
                enCurrentAmt = true,
                enPayAmt = false,
                enIntRate = true
            )
        }
    }

    private fun initRadioButtonLoan() {

        // get listener for Loan
        loanRadioButton.setOnClickListener {
            clearMessage()
            currentFinance.accounttype = "Loan"
            initMainScreen(
                enAccountNumber = true,
                enInitialAmt = true,
                enCurrentAmt = true,
                enPayAmt = true,
                enIntRate = true
            )
        }
    }

    private fun initRadioButtonCheckingAccount() {

        // get listener for Checking Account
        checkingAccountRadioButton.setOnClickListener {
            clearMessage()
            currentFinance.accounttype = "CheckingAccount"
            initMainScreen(
                enAccountNumber = true,
                enInitialAmt = false,
                enCurrentAmt = true,
                enPayAmt = false,
                enIntRate = false
            )
        }
    }

    private fun initButtonCancel() {

        // get listener for Cancel
        cancelButton.setOnClickListener {
            initResetScreen()
            clearMessage()
//            selectProductRadioGroup.clearCheck()
//            clearMessage()
//            clearFinanceData()
//
//            initMainScreen(
//                enAccountNumber = false,
//                enInitialAmt = false,
//                enCurrentAmt = false,
//                enPayAmt = false,
//                enIntRate = false
//            )

        }
    }

    private fun clearFinanceData() {
        currentFinance = Finance()
    }

    private fun initButtonSave() {

        // get listener for Cancel
        saveButton.setOnClickListener {
            var wasSuccessful: Boolean = false
            val ds = FinanceDataSource(this@MainActivity)

            try {
                ds.open()
                if (currentFinance.financeID == -1) {
                    val inserted = ds.insertFinance(currentFinance)
                    if (inserted) {
                        val newID = ds.getLastFinanceID()
                        newID.let { currentFinance.financeID = it }
                        wasSuccessful = true
                    }
                } else {
                    wasSuccessful = false
                }
            ds.close()
        } catch (e: Exception) {
            wasSuccessful = false
        } finally {
            ds.close()
        }
        if (wasSuccessful) {
            displayMessage("Saved Successfully")
            initResetScreen()

        } else {
            displayMessage("Unable to Save")
        }
    }
    }  // end of initButtonSave

    private fun initTextChangedEvents() {
        // bellow code to capture events
        val etAccountNumber: EditText = findViewById(R.id.editTextAccountNumber)
        etAccountNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No Action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No Action needed
            }

            override fun afterTextChanged(s: Editable?) {
                currentFinance.accountnumber = etAccountNumber.text.toString()
            }
        }) // end of addTextChangedListener Account number

        // Initial Balance
        val etInitialBalance: EditText = findViewById(R.id.editTextInitialBalance)
        etInitialBalance.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No Action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No Action needed
            }

            override fun afterTextChanged(s: Editable?) {
                currentFinance.initialbalance = etInitialBalance.text.toString()
            }
        }) // end of Initial Balance

        // Current Balance
        val etCurrentBalance: EditText = findViewById(R.id.editTextCurrentBalance)
        etCurrentBalance.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No Action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No Action needed
            }

            override fun afterTextChanged(s: Editable?) {
                currentFinance.currentbalance = etCurrentBalance.text.toString()
            }
        }) // End of Current Balance

        // Payment Amount
        val etPaymentBalance: EditText = findViewById(R.id.editTextPaymentAmount)
        etPaymentBalance.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No Action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No Action needed
            }

            override fun afterTextChanged(s: Editable?) {
                currentFinance.paymentamount = etPaymentBalance.text.toString()
            }
        }) // End of Payment Amount

        // Interest Rate
        val etInterestRate: EditText = findViewById(R.id.editTextInterestRate)
        etInterestRate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No Action needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No Action needed
            }

            override fun afterTextChanged(s: Editable?) {
                currentFinance.interestrate = etInterestRate.text.toString()
            }
        }) // End of Interest Rate
    }  // end of initTextChangedEvents

    private fun initMainScreen(
        enAccountNumber: Boolean,
        enInitialAmt: Boolean,
        enCurrentAmt: Boolean,
        enPayAmt: Boolean,
        enIntRate: Boolean
    ) {


        accountNumberEditText.isEnabled = enAccountNumber
        initialBalanceEditText.isEnabled = enInitialAmt
        currentBalanceEditText.isEnabled = enCurrentAmt
        paymentAmountEditText.isEnabled = enPayAmt
        interestRateEditText.isEnabled = enIntRate

        val alphaEnabled = 1.0f
        val alphaDisabled = 0.25f

        if (enAccountNumber) {
            accountNumberTextView.alpha = alphaEnabled
            accountNumberEditText.alpha = alphaEnabled
        } else {
            accountNumberTextView.alpha = alphaDisabled
            accountNumberEditText.alpha = alphaDisabled
            accountNumberEditText.text.clear()
        }

        if (enInitialAmt) {
            initialBalanceTextView.alpha = alphaEnabled
            initialBalanceEditText.alpha = alphaEnabled

        } else {
            initialBalanceTextView.alpha = alphaDisabled
            initialBalanceEditText.alpha = alphaDisabled
            initialBalanceEditText.text.clear()
        }

        if (enCurrentAmt) {
            currentBalanceTextView.alpha = alphaEnabled
            currentBalanceEditText.alpha = alphaEnabled

        } else {
            currentBalanceTextView.alpha = alphaDisabled
            currentBalanceEditText.alpha = alphaDisabled
            currentBalanceEditText.text.clear()
        }

        if (enPayAmt) {
            paymentAmountTextView.alpha = alphaEnabled
            paymentAmountEditText.alpha = alphaEnabled

        } else {
            paymentAmountTextView.alpha = alphaDisabled
            paymentAmountEditText.alpha = alphaDisabled
            paymentAmountEditText.text.clear()
        }

        if (enIntRate) {
            interestRateTextView.alpha = alphaEnabled
            interestRateEditText.alpha = alphaEnabled

        } else {
            interestRateTextView.alpha = alphaDisabled
            interestRateEditText.alpha = alphaDisabled
            interestRateEditText.text.clear()
        }


    }  // End of initMainScreen

    private fun displayMessage(message: String) {
        messageTextView.text = message
        messageTextView.visibility = View.VISIBLE

    } // End of displayMessage

    private fun clearMessage() {
        messageTextView.text = ""
        messageTextView.visibility = View.GONE
    }// End of clearMessage

    } // End of Main Activity



