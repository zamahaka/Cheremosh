package org.zamahaka.cheremosh.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.zamahaka.cheremosh.Url

/**
 * Created by Yura Stetsyc on 19-9-17.
 */
fun ImageView.load(url: Url) = Picasso.with(context).load(url).fit().centerInside().into(this)