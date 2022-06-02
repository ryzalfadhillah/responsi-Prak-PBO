/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Interface.ListenerMovie;
import View.MovieView;
import Database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryzal
 */
public class MovieModel {
    private String judul;
    private String alur;
    private String penokohan;
    private String akting;
    private double nilai;
    private ListenerMovie listenerMovie;
        
    Koneksi conn = new Koneksi();

    protected void fireonChange(){
        if(listenerMovie != null){
            listenerMovie.onChange(this);
        }
    }
    
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
        fireonChange();
    }

    public String getAlur() {
        return alur;
    }

    public void setAlur(String alur) {
        this.alur = alur;
        fireonChange();
    }

    public String getPenokohan() {
        return penokohan;
    }

    public void setPenokohan(String penokohan) {
        this.penokohan = penokohan;
        fireonChange();
    }

    public String getAkting() {
        return akting;
    }

    public void setAkting(String akting) {
        this.akting = akting;
        fireonChange();
    }

    public double getNilai(MovieView view) {
        String valur = view.getfAlur().getText();
        String vpenokohan = view.getfPenokohan().getText();
        String vakting = view.getfAkting().getText();
        
        double alur = Double.parseDouble(valur);
        double penokohan = Double.parseDouble(vpenokohan);
        double akting = Double.parseDouble(vakting);
        
        nilai = (alur + penokohan + akting)/3;
        
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
        fireonChange();
    }

    public ListenerMovie getListenerMovie() {
        return listenerMovie;
    }

    public void setListenerMovie(ListenerMovie listenerMovie) {
        this.listenerMovie = listenerMovie;
    }
            
    public void clear(){
        setJudul("");
        setAlur("");
        setPenokohan("");
        setAkting("");
    }
    
    public void lihat(MovieView){
    
    }
    
    public void tambah(MovieView view){
        try{
            String vjudul = view.getfJudul().getText();
            String valur = view.getfAlur().getText();
            String vpenokohan = view.getfPenokohan().getText();
            String vakting = view.getfAkting().getText();
            double vnilai = getNilai(view); 
                                    
            String query = "INSERT INTO `movie`(`judul`, `alur`, `penokohan`, `akting`, `nilai`) " + "VALUES ('" 
                    + vjudul + "', '" + valur + "', '" + vpenokohan + 
                    "', '" + vakting + "', '" + vnilai + "')";
            
            conn.statement = conn.connection.createStatement();
            conn.statement.execute(query);
            
            JOptionPane.showMessageDialog(view, "Berhasil menambahkan penilaian");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }
}
