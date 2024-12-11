package com.wsb.findapart.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsb.findapart.adapter.ListAdapter
import com.wsb.findapart.databinding.FragmentListBinding
import com.wsb.findapart.model.ListItem

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val items = loadCSVFromAssets("data.csv")

        binding.recyclerView.adapter = ListAdapter(items)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadCSVFromAssets(fileName: String): List<ListItem> {
        return try {
            requireContext().assets.open(fileName).bufferedReader().use { reader ->
                reader.lineSequence().drop(1)
                    .mapNotNull { line ->
                        val tokens = line.split(",")
                        if (tokens.size >= 26) {
                            ListItem(
                                id = tokens[0].trim(),
                                city = tokens[1].trim(),
                                type = tokens[2].trim(),
                                area = tokens[3].trim(),
                                room = tokens[4].trim(),
                                floor = tokens[5].trim(),
                                floorcount = tokens[6].trim(),
                                buildyear = tokens[7].trim(),
                                centredistance = tokens[8].trim(),
                                poicount = tokens[9].trim(),
                                schooldistance = tokens[10].trim(),
                                clinicdistance = tokens[11].trim(),
                                postofficedistance = tokens[12].trim(),
                                kindergartendistance = tokens[13].trim(),
                                restaurantdistance = tokens[14].trim(),
                                collegedistance = tokens[15].trim(),
                                pharmacydistance = tokens[16].trim(),
                                ownership = tokens[17].trim(),
                                buildingmaterial = tokens[18].trim(),
                                condition = tokens[19].trim(),
                                hasparkingspace = tokens[20].trim(),
                                hasbalcony = tokens[21].trim(),
                                haselevator = tokens[22].trim(),
                                hassecurity = tokens[23].trim(),
                                hasstorageroom = tokens[24].trim(),
                                price = tokens[25].trim()
                            )
                        } else null
                    }.toList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}