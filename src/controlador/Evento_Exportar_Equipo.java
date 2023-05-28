package controlador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vista.Visualizar_Empleado;
import vista.Visualizar_Equipo;

public class Evento_Exportar_Equipo implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		
		
		File archivo;
		
		JFileChooser nuevo=new JFileChooser("src/Documentos Exportados/Equipos");	
		
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Documentos PDF", "pdf");
		
		nuevo.setFileFilter(filter);
		
		nuevo.requestFocusInWindow();

	    int op = nuevo.showDialog(null, "Guardar");
	    
	    if(op==JFileChooser.APPROVE_OPTION) {
	    	
	    	archivo=nuevo.getSelectedFile();
	    	
	    	File otro_archivo=new File(archivo.getAbsolutePath()+".pdf"); 	
	    	
	    	 
				if(otro_archivo.exists()){
					 
					 

				     JOptionPane.showMessageDialog(null,"El Archivo ya existe");
				     
				     return;

				 }else {
					 
					 
					 String ruta_archivo=archivo.getAbsolutePath();
					 
					 
					 Document doc = new Document();
						try {
							PdfWriter.getInstance(doc, new FileOutputStream(ruta_archivo+".pdf"));
							doc.open();
							PdfPTable table = new PdfPTable(7);
							
							JTable tabla=Visualizar_Equipo.tabla;
							
							int num_filas=tabla.getRowCount();
									
							tabla.setRowHeight(35);
							
							table.setWidthPercentage(100);
							
							table.setWidths(new float[] {85,85,85,85,85,85,85});
							
							Paragraph titulo = new Paragraph("Equipos \n\n");
							titulo.getFont().setStyle(Font.BOLD);
							titulo.getFont().setSize(15);
							titulo.setAlignment(Element.ALIGN_CENTER);
							
							Paragraph nombre = new Paragraph("Nombre");
						    nombre.getFont().setStyle(Font.BOLD);
						    nombre.getFont().setSize(7);
						    
						    
						    Paragraph marca = new Paragraph("Marca");
						    marca.getFont().setStyle(Font.BOLD);
						    marca.getFont().setSize(7);
						    
						    
						    Paragraph modelo = new Paragraph("Modelo");
						    modelo.getFont().setStyle(Font.BOLD);
						    modelo.getFont().setSize(7);
						    
						    
						    Paragraph codigo = new Paragraph("Código");
						    codigo.getFont().setStyle(Font.BOLD);
						    codigo.getFont().setSize(7);
						    
						    
						    Paragraph numero = new Paragraph("Número");
						    numero.getFont().setStyle(Font.BOLD);
						    numero.getFont().setSize(7);
						    
						    
						    Paragraph estado = new Paragraph("Estado");
						    estado.getFont().setStyle(Font.BOLD);
						    estado.getFont().setSize(7);
						    
						    
						    Paragraph ubicacion = new Paragraph("Ubicación");
						    ubicacion.getFont().setStyle(Font.BOLD);
						    ubicacion.getFont().setSize(7);		    
						    
							
							
							
							
							Stream.of(nombre, marca, modelo, codigo, numero, estado, ubicacion).forEach(ct -> {
								PdfPCell header = new PdfPCell();
								header.setBackgroundColor(BaseColor.LIGHT_GRAY);						
								header.setBorderWidth(2);
								header.setHorizontalAlignment(Element.ALIGN_CENTER);
								header.setFixedHeight(17);
								header.setPhrase(new Phrase(ct));						
								table.addCell(header);
								
								
							});
							
							int [] seleccionadas=tabla.getSelectedRows();
							
							if(seleccionadas.length>0) {
								
								for (int i = 0; i < seleccionadas.length; i++) {

									
									Paragraph columna1 = new Paragraph(tabla.getValueAt(seleccionadas[i], 0).toString());
								    columna1.getFont().setStyle(Font.BOLD);
								    columna1.getFont().setSize(5);
								    
								    
								    Paragraph columna2 = new Paragraph(tabla.getValueAt(seleccionadas[i], 1).toString());
								    columna2.getFont().setStyle(Font.BOLD);
								    columna2.getFont().setSize(5);
								    
								    
								    Paragraph columna3 = new Paragraph(tabla.getValueAt(seleccionadas[i], 2).toString());
								    columna3.getFont().setStyle(Font.BOLD);
								    columna3.getFont().setSize(5);
								    
								    
								    Paragraph columna4 = new Paragraph(tabla.getValueAt(seleccionadas[i], 3).toString());
								    columna4.getFont().setStyle(Font.BOLD);
								    columna4.getFont().setSize(5);
								    
								    
								    Paragraph columna5 = new Paragraph(tabla.getValueAt(seleccionadas[i], 4).toString());
								    columna5.getFont().setStyle(Font.BOLD);
								    columna5.getFont().setSize(5);
								    
								    
								    Paragraph columna6 = new Paragraph(tabla.getValueAt(seleccionadas[i], 5).toString());
								    columna6.getFont().setStyle(Font.BOLD);
								    columna6.getFont().setSize(5);
								    
								    
								    Paragraph columna7 = new Paragraph(tabla.getValueAt(seleccionadas[i], 6).toString());
								    columna7.getFont().setStyle(Font.BOLD);
								    columna7.getFont().setSize(5);
								    
								   
								   				
									
									table.addCell(columna1);
									table.addCell(columna2);
									table.addCell(columna3);
									table.addCell(columna4);
									table.addCell(columna5);
									table.addCell(columna6);
									table.addCell(columna7);
									
								}
								
							}else {
								
								for (int i = 0; i < tabla.getRowCount(); i++) {

									
									Paragraph columna1 = new Paragraph(tabla.getValueAt(i, 0).toString());
								    columna1.getFont().setStyle(Font.BOLD);
								    columna1.getFont().setSize(5);
								    
								    
								    Paragraph columna2 = new Paragraph(tabla.getValueAt(i, 1).toString());
								    columna2.getFont().setStyle(Font.BOLD);
								    columna2.getFont().setSize(5);
								    
								    
								    Paragraph columna3 = new Paragraph(tabla.getValueAt(i, 2).toString());
								    columna3.getFont().setStyle(Font.BOLD);
								    columna3.getFont().setSize(5);
								    
								    
								    Paragraph columna4 = new Paragraph(tabla.getValueAt(i, 3).toString());
								    columna4.getFont().setStyle(Font.BOLD);
								    columna4.getFont().setSize(5);
								    
								    
								    Paragraph columna5 = new Paragraph(tabla.getValueAt(i, 4).toString());
								    columna5.getFont().setStyle(Font.BOLD);
								    columna5.getFont().setSize(5);
								    
								    
								    Paragraph columna6 = new Paragraph(tabla.getValueAt(i, 5).toString());
								    columna6.getFont().setStyle(Font.BOLD);
								    columna6.getFont().setSize(5);
								    
								    
								    Paragraph columna7 = new Paragraph(tabla.getValueAt(i, 6).toString());
								    columna7.getFont().setStyle(Font.BOLD);
								    columna7.getFont().setSize(5);
								    
								   
								   				
									
									table.addCell(columna1);
									table.addCell(columna2);
									table.addCell(columna3);
									table.addCell(columna4);
									table.addCell(columna5);
									table.addCell(columna6);
									table.addCell(columna7);
									
								}
								
							}
							
							
							
							
							if(num_filas>1 && tabla.getSelectedRows().length!=1) {				
								
								doc.add(titulo);
								doc.add(table);
								doc.close();
								
							}else {
								
									boolean mostrar_imagen=false;
								
									
									try {
										ruta=Visualizar_Equipo.tabla.getValueAt(Visualizar_Equipo.tabla.getSelectedRow(), 8).toString();
										
										imagen=Image.getInstance(ruta);
									} catch (Exception e1) {
										
										/*try {
											
											//------------------------------aqui tengo que poner la imagen de que no hay imagen------------------
											
																	
											
											imagen=Image.getInstance("src/vista/Fondo Puchi.jpg");
										} catch (MalformedURLException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
										//e1.printStackTrace();*/
										
										mostrar_imagen=true;
									}			
									
									
								
								doc.add(titulo);								
								doc.add(table);
								if(mostrar_imagen==false) {	
									imagen.setAlignment(Element.ALIGN_CENTER);
									imagen.scaleAbsoluteWidth(250f);
									imagen.scaleAbsoluteHeight(150f);
									
									doc.add(imagen);									
								}								
								doc.close();
								
							}
							
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
							
						} catch (DocumentException e1) {
							
							e1.printStackTrace();
							
						}
						
						JOptionPane.showMessageDialog(null, "Equipos exportado con exito.", null, JOptionPane.INFORMATION_MESSAGE);
					 
					 
				 }
		
	    }		
	    
	    
		
		
	}
	
	String ruta;
	
	Image imagen;

}
