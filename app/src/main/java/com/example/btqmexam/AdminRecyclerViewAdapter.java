  package com.example.btqmexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

  public class AdminRecyclerViewAdapter extends FirebaseRecyclerAdapter<modelAdmin,AdminRecyclerViewAdapter.myviewholder> {


      public AdminRecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<modelAdmin> options) {
          super(options);
      }

      @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modelAdmin model) {

          holder.name.setText(model.getName());
          holder.claass.setText(model.getClasss());
          holder.section.setText(model.getSection());
          holder.branch.setText(model.getBranch());
          holder.phoneNo.setText(model.getMobileNo());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list_design,parent,false);


        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView name,claass,section,branch,phoneNo;
        Button deleteButtn,setCodeButtn;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adminListName);
            claass = itemView.findViewById(R.id.adminListclass);
            section = itemView.findViewById(R.id.adminListSection);
            branch = itemView.findViewById(R.id.adminListBranch);
            phoneNo = itemView.findViewById(R.id.adminListPhoneNo);
            deleteButtn = itemView.findViewById(R.id.teacherDelete);
            setCodeButtn = itemView.findViewById(R.id.teacherSetCode);


        }
    }

}
