package com.saikat.noteapp

import android.content.Context
import android.graphics.Color
import android.net.wifi.p2p.WifiP2pManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener:INotesRvAdapter):RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    val colorm= mutableListOf<String>("#EAF18D","#F3C98A")

    private val allNotes=ArrayList<Note>()


    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtNote:TextView=itemView.findViewById(R.id.txtNote)
        val btnDelete:ImageView=itemView.findViewById(R.id.imgDelete)
        val everyItem:ConstraintLayout=itemView.findViewById(R.id.everyItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_view,
            parent,
            false))
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.txtNote.text=currentNote.text.trim()
        holder.everyItem.setBackgroundColor(Color.parseColor(colorm[position%2]))
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRvAdapter{
    fun onItemClicked(note:Note)
}