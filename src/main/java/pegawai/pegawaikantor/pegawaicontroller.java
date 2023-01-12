/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegawai.pegawaikantor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lenovo
 */
@RestController
@ResponseBody
public class pegawaicontroller {
    
    //membuat objek baru untuk barang dan jpa controller
    Pegawaikantor pegawai = new Pegawaikantor();
    PegawaikantorJpaController pegawaibaruJPA = new PegawaikantorJpaController();
    
    //memanggil data yang ada di database kita 
    @GetMapping("/get")
    public List<Pegawaikantor>pgw(){
        //mengembalikan nilai yang ada didalam database 
        return pegawaibaruJPA.findPegawaikantorEntities();
    }
    
    //Create atau menambahkan data 
    @PostMapping("/Post")
    public String insertpegawaikantor (HttpEntity<String>send)throws Exception {
        Pegawaikantor Pegawaibaru = new Pegawaikantor();
        String baru= send.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        
        Pegawaibaru = mapper.readValue(baru,Pegawaikantor.class);
        pegawaibaruJPA.create(Pegawaibaru);
        
        return "id" + Pegawaibaru.getName().toString() + "telah dibuat ";
    } 
    
    @PutMapping("/Put")
    public String updatepegawaikantor (HttpEntity<String>send)throws Exception{
        Pegawaikantor Pegawaibaru = new Pegawaikantor();
        String baru  = send.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
        Pegawaibaru = mapper.convertValue(baru,Pegawaikantor.class );
        pegawaibaruJPA.edit(Pegawaibaru);
        
        return "id" + Pegawaibaru.getName().toString() + "telah diupdate ";
        
    }
    
   @DeleteMapping("/Delete")
    public String deletepegawaikantor (HttpEntity<String>send) throws  Exception{
        Pegawaikantor Pegawaibaru = new Pegawaikantor();
        String baru = send.getBody();
        
        ObjectMapper mapper = new ObjectMapper ();
        mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, false);
        Pegawaibaru = mapper.readValue(baru, Pegawaikantor.class);
        
        
       return "id" + Pegawaibaru.getName().toString() + "telah dihapus  ";
        
        
    }
            
}
