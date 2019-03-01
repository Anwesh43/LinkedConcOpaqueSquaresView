package com.anwesh.uiprojects.linkedconcopaquesquaresview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.concopaquesquaresview.ConcOpaqueSquaresView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConcOpaqueSquaresView.create(this)
    }
}
