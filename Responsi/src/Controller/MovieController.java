/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MovieModel;
import View.MovieView;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryzal
 */
public class MovieController {
    private MovieModel model;
            
    public void setMovie(MovieModel model){
        this.model = model;
    }
    
    public void clear(MovieView view){
        String judul = view.getfJudul().getText();
        String alur = view.getfAlur().getText();
        String penokohan = view.getfPenokohan().getText();
        String akting = view.getfAkting().getText();
        
        if(judul.equals("") && alur.equals("") && penokohan.equals("") && akting.equals("")){
            
        }else{
            model.clear();
        }
    }
    
    public void tambah(MovieView view){
        String judul = view.getfJudul().getText();
        String alur = view.getfAlur().getText();
        String penokohan = view.getfPenokohan().getText();
        String akting = view.getfAkting().getText();
        
        if(judul.equals("") || alur.equals("") || penokohan.equals("") || akting.equals("")){
            JOptionPane.showMessageDialog(view, "Form tidak boleh ada yang kosong");
        }else{
            model.tambah(view);
        }
    }
}
