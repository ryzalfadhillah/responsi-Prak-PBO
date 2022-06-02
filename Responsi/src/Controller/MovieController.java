/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MovieModel;
import View.MovieView;
import java.awt.HeadlessException;
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
    
    public void getData(MovieView view){
        int pilih = view.getjTable1().getSelectedRow();
            
        String judul = (String) view.getjTable1().getValueAt(pilih, 0);
        view.getfJudul().setText(judul);
        model.setJudul(judul);
        String alur = (String) view.getjTable1().getValueAt(pilih, 1);
        view.getfAlur().setText(alur);
        String penokohan = (String) view.getjTable1().getValueAt(pilih, 2);
        view.getfPenokohan().setText(penokohan);
        String akting = (String) view.getjTable1().getValueAt(pilih, 3);
        view.getfAkting().setText(akting);
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
  
    public void lihat(MovieView view){
        model.lihat(view);
    }
    
    public void tambah(MovieView view){
        try{
            String judul = view.getfJudul().getText();
            String alur = view.getfAlur().getText();
            String penokohan = view.getfPenokohan().getText();
            String akting = view.getfAkting().getText();

            double valur = Double.parseDouble(alur);
            double vpenokohan = Double.parseDouble(penokohan);
            double vakting = Double.parseDouble(akting);

            if(judul.trim().equals("")){
                JOptionPane.showMessageDialog(view, "Judul tidak boleh ada yang kosong");
            }else if(alur.trim().equals("")){
                 JOptionPane.showMessageDialog(view, "Alur tidak boleh kosong");  
            }else if(penokohan.trim().equals("")){
                JOptionPane.showMessageDialog(view, "Penokohan tidak boleh kosong");
            }else if(akting.trim().equals("")){
                JOptionPane.showMessageDialog(view, "Akting tidak boleh kosong");
            }else{
                if(valur > 5 || valur < 0 || vpenokohan > 5 || vpenokohan < 0 || vakting > 5 || vakting < 0 ){
                    JOptionPane.showMessageDialog(view, "Nilai harus antara 0-5");
                }else{
                    model.tambah(view);
                    model.lihat(view);
                    model.clear();
                }
            }  
        }catch(HeadlessException | NumberFormatException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Alur, penokohan, dan akting harus berupa angka");
        }     
        
    }

    public void update(MovieView view){
        int opsi = JOptionPane.showConfirmDialog(view, "Yakin ingin update data?");
        if(opsi == JOptionPane.YES_OPTION){
            model.update(view);
            model.lihat(view);
            model.clear();
        }
    }
    
    public void delete(MovieView view){
        int opsi = JOptionPane.showConfirmDialog(view, "Yakin ingin hapus data?");
        if(opsi == JOptionPane.YES_OPTION){
            model.delete(view);
            model.lihat(view);
        }
    }
}
