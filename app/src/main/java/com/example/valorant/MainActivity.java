package com.example.valorant;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.example.valorant.Client.ApiClient;
import com.example.valorant.adapters.AgentAdapter;
import com.example.valorant.adapters.CharacterAdapter;
import com.example.valorant.apiservice.ValorantApiService;
import com.example.valorant.apiservice.ValorantResponse;
import com.example.valorant.models.Agent;
import com.example.valorant.models.Character;
import com.example.valorant.network.ApiResponse;
import com.google.android.material.tabs.TabLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AgentAdapter adapter;
    TabLayout tabLayout;
    private EditText searchAgent;
    private List<Agent> originalAgentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        recyclerView.startAnimation(fadeIn);

            searchAgent = findViewById(R.id.search_agents_edittext);

        fetchAgents();

        searchAgent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    filterAgents(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void fetchAgents() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://valorant-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ValorantApiService apiService = retrofit.create(ValorantApiService.class);
        Call<ValorantResponse> call = apiService.getAgents();

        call.enqueue(new Callback<ValorantResponse>() {
            @Override
            public void onResponse(Call<ValorantResponse> call, Response<ValorantResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("MainActivity", "Response: " + response.toString());
                    List<Agent> agents = response.body().getData();
                    originalAgentList = agents; //save the original list
//                    Agent selectedAgent = agents.get(0);
//                    AgentSingleton.getInstance().setAgent(selectedAgent);
                    adapter = new AgentAdapter(MainActivity.this, agents);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load agents", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValorantResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void filterAgents(String query){
        List<Agent> filteredList = new ArrayList<>();
        for (Agent agent: originalAgentList) {
            if (agent.getName().toLowerCase().contains(query.toLowerCase())){
                filteredList.add(agent);
            }
        }
        adapter.updateList(filteredList);
    }
}
