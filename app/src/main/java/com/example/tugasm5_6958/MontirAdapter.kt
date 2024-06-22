package com.example.tugasm5_6958

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasm5_6958.CurrencyUtils.toRupiah

class MontirAdapter(
    var data: MutableList<User>,
    val onDetailClickListener: (User) -> Unit
) : RecyclerView.Adapter<MontirAdapter.ViewHolder>() {

    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val tvMINama: TextView = row.findViewById(R.id.tvMINama)
        val tvMIPengalaman: TextView = row.findViewById(R.id.tvMIPengalaman)
        val tvMIBiaya: TextView = row.findViewById(R.id.tvMIBiaya)
        val btnMIDetail: Button = row.findViewById(R.id.btnMIDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            R.layout.montir_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val montir = data[position]
        holder.tvMINama.text = "Nama : " + montir.name
        holder.tvMIPengalaman.text = "Pengalaman : " + montir.lengthOfEmployment
        holder.tvMIBiaya.text = "Biaya : " + montir.price!!.toRupiah()
        holder.btnMIDetail.setOnClickListener {
            onDetailClickListener.invoke(montir)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}