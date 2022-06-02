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
import javax.swing.table.DefaultTableModel;

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
    
    public void lihat(MovieView view){
        try{
            DefaultTableModel tabelData = new DefaultTableModel();
            
            view.getjTable1().setModel(tabelData);

            tabelData.addColumn("Judul");
            tabelData.addColumn("Alur");
            tabelData.addColumn("Penokohan");
            tabelData.addColumn("Akting");
            tabelData.addColumn("Nilai");
            
            String query = "SELECT * FROM movie";
            
            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);
            
            while(rs.next()){
                Object[] obj = new Object[5];
                
                obj[0] = rs.getString("judul");
                obj[1] = rs.getString("alur");
                obj[2] = rs.getString("penokohan");
                obj[3] = rs.getString("akting");
                obj[4] = rs.getString("nilai");
                
                tabelData.addRow(obj);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }       
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
            JOptionPane.showMessageDialog(view, "Data sudah ada");
        }
    }
    
    public void update(MovieView view){
        try{
            String judul = getJudul();                      
            
            String vjudul = view.getfJudul().getText();
            String valur = view.getfAlur().getText();
            String vpenokohan = view.getfPenokohan().getText();
            String vakting = view.getfAkting().getText();
            double vnilai = getNilai(view); 
                                   
            String query = "UPDATE movie SET judul = '" + vjudul + 
                    "', alur = '" + valur + 
                    "', penokohan = '" + vpenokohan + 
                    "', akting = '" + vakting + 
                    "', nilai = '" + vnilai + 
                    "' WHERE movie.judul = '" + judul + "';";
            
            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);
            
            JOptionPane.showMessageDialog(view, "Berhasil update data");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    
    }
            
    public void delete(MovieView view){
        try{
            int pilih = view.getjTable1().getSelectedRow();
            String judul = (String) view.getjTable1().getValueAt(pilih, 0);

            String query = "DELETE FROM `movie` WHERE `judul` = '" + judul + "'";

            conn.statement = conn.connection.createStatement();
            conn.statement.execute(query);
            
            JOptionPane.showMessageDialog(view, "Berhasil menghapus data");            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }        
    }
}
