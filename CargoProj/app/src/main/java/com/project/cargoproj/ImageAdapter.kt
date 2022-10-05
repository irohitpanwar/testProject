package com.project.cargoproj
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.project.cargoproj.model.ResponseModel
import com.squareup.picasso.Picasso

class ImageAdapter(
    // on below line we are passing variables as list
    private val photoList: ArrayList<ResponseModel>,
) : RecyclerView.Adapter<ImageAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageAdapter.PhotoViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.image_card,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return ImageAdapter.PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageAdapter.PhotoViewHolder, position: Int) {
        // on below line we are loading image from image url in our image view.
        Picasso.get().load(photoList.get(position).url).into(holder.photoIV)
    }

    override fun getItemCount(): Int {
        // on below line we are returning
        // the size of our list
        return photoList.size
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our image view.
        val photoIV: ImageView = itemView.findViewById(R.id.idIVImage)
    }

}
