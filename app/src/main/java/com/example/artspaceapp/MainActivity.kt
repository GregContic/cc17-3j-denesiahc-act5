package com.example.artspaceapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

	private lateinit var artworkImageView: ImageView
	private lateinit var artworkTitleTextView: TextView
	private lateinit var artworkAuthorTextView: TextView
	private lateinit var artworkDescriptionTextView: TextView
	private lateinit var previousButton: MaterialButton
	private lateinit var nextButton: MaterialButton

	private val artworks = listOf(
		Artwork(
			R.drawable.blondcover_final,
			"Blond",
			"Frank Ocean",
			"Blonde is the second studio album by the American singer Frank Ocean. It was released on August 20, 2016, and the album talks about the dealings of love, loss and heartbreaks."
		),
		Artwork(
			R.drawable.tpab,
			"To Pimp a Butterfly",
			"Kendrick Lamar",
			"To Pimp a Butterfly is the third studio album by American rapper Kendrick Lamar. It was released on March 15, 2015, and it deals with the sociopolitical state of America. It has been a staple of African-American culture and a highlight during the year."
		),
		Artwork(
			R.drawable.foresthd,
			"2014 Forest Hills Drive",
			"J.Cole",
			"2014 Forest Hills Drive is the third studio album by American rapper J. Cole, released on December 9, 2014, this album is considered a genre cult classic and a well regarded project within the music industry."
		),
		Artwork(
			R.drawable.sos,
			"SOS",
			"SZA",
			"SOS is the second studio album by American singer-songwriter SZA. It was released on December 9, 2022. This album explores strong feelings of hurt, lost love, and revenge for wrongdoing. An R&B classic."
		),
		Artwork(
			R.drawable.currents,
			"Currents",
			"Tame Impala",
			"Currents is the third studio album by Australian musical project Tame Impala, released on 17 July 2015, this album is about the process of personal transformation, which many critics interpreted to be the result of a romantic breakup."
		)
	)

	private var currentArtworkIndex = 0
	private var isFirstArtwork: Boolean = true
	private var isLastArtwork: Boolean = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		artworkImageView = findViewById(R.id.artworkImageView)
		artworkTitleTextView = findViewById(R.id.artworkTitleTextView)
		artworkAuthorTextView = findViewById(R.id.artworkAuthorTextView)
		artworkDescriptionTextView = findViewById(R.id.artworkDescriptionTextView)
		previousButton = findViewById(R.id.previousButton)
		nextButton = findViewById(R.id.nextButton)

		updateArtworkDisplay()
		updateNavigationButtons()

		previousButton.setOnClickListener {
			navigateArtwork(false)
		}

		nextButton.setOnClickListener {
			navigateArtwork(true)
		}
	}

	private fun navigateArtwork(goForward: Boolean) {
		currentArtworkIndex = when {
			goForward -> (currentArtworkIndex + 1) % artworks.size
			else -> (currentArtworkIndex - 1 + artworks.size) % artworks.size
		}
		updateArtworkDisplay()
		updateNavigationButtons()
	}

	private fun updateArtworkDisplay() {
		val currentArtwork = artworks[currentArtworkIndex]
		artworkImageView.setImageResource(currentArtwork.imageResourceId)
		artworkTitleTextView.text = currentArtwork.title
		artworkAuthorTextView.text = currentArtwork.author
		artworkDescriptionTextView.text = currentArtwork.description
	}

	private fun updateNavigationButtons() {
		isFirstArtwork = currentArtworkIndex == 0
		isLastArtwork = currentArtworkIndex == artworks.size - 1

		previousButton.isEnabled = !isFirstArtwork
		nextButton.isEnabled = !isLastArtwork

		previousButton.alpha = if (isFirstArtwork) 0.5f else 1.0f
		nextButton.alpha = if (isLastArtwork) 0.5f else 1.0f
	}
}

data class Artwork(
	val imageResourceId: Int,
	val title: String,
	val author: String,
	val description: String
)
