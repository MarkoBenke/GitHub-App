package com.marko.githubapp

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * Activity that is used for testing purposes. It has @AndroidEntryPoint annotation which means
 * that this activity can hold all fragments with same annotation.
 */
@AndroidEntryPoint
class HiltTestActivity: AppCompatActivity()