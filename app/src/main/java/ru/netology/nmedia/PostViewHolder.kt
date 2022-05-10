package ru.netology.nmedia

import ru.netology.nmedia.databinding.CardPostBinding
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class PostViewHolder (val binding: CardPostBinding, val onLikeListener: OnLikeListener, val onShareListener: OnShareListener) : RecyclerView.ViewHolder(binding.root){
    fun bind (post: Post) {
        binding.apply {
            textViewAuthorName.text = post.author
            textViewAuthorDate.text = post.publishedDate
            textViewContent.text = post.content
            TextViewFavoriteBorder.text = compactDecimalFormat(post.countLikes)
            TextViewShare.text = compactDecimalFormat(post.countShare)
            TextViewVisibility.text = compactDecimalFormat(post.countVisibility)

            binding.ImageViewFavoriteBorder.setImageResource(
                if (post.likeByMe) R.drawable.ic_baseline_favorite_24_red else R.drawable.ic_baseline_favorite_border_24
            )

            ImageViewFavoriteBorder.setOnClickListener { onLikeListener(post) }
            ImageViewShare.setOnClickListener { onShareListener(post) }

        }

    }

    private fun compactDecimalFormat(number: Int): String {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')

        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3

        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }

}
