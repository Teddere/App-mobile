package View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemaplace.R;

import java.util.ArrayList;

import Model.Match;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private ArrayList<Match> matchList;
    private OnMatchListerner OnMatchListerner;
    public MatchAdapter(ArrayList<Match> matchList,OnMatchListerner OnMatchListerner) {
        this.matchList = matchList;
        this.OnMatchListerner=OnMatchListerner;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_soccer,parent,false);

        return  new MatchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final   ViewHolder holder, int position) {

            Match match = matchList.get(position);

            holder.teamsA.setText(match.getTeamsA());
            holder.teamsB.setText(match.getTeamsB());
            holder.dateMeet.setText(match.getMeet());
            holder.square.setText(match.getSquare()+" places");
            holder.place.setText(match.getPlace());
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(OnMatchListerner !=null){
                        OnMatchListerner.onClick(match);
                    }

                }
            });

    }

    @Override
    public int getItemCount() {
        //Nombre de ligne affiche dans la liste
        return matchList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        public TextView teamsA,teamsB,dateMeet,place,square,title;
        public View root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamsA=(TextView) itemView.findViewById(R.id.tv_teamsA);
            teamsB=(TextView) itemView.findViewById(R.id.tv_equipeB);
            dateMeet=(TextView) itemView.findViewById(R.id.tv_dateMeet);
            square=(TextView) itemView.findViewById(R.id.tv_square);
            place=(TextView)  itemView.findViewById(R.id.tv_place);
            root=itemView.findViewById(R.id.root);

        }
    }
    public interface OnMatchListerner{
        void onClick(Match match);
    }

}
