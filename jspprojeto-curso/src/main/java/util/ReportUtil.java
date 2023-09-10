package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({ "rawtypes" })
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio, ServletContext servletContext)
			throws Exception {

		/* cria a lista de dados que vem do SQL */
		JRBeanCollectionDataSource jrben = new JRBeanCollectionDataSource(listaDados);

		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap<>(), jrben);

		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}

	// metodo para receber um sub relatorio
	public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio,HashMap<String, Object> params, ServletContext servletContext) throws Exception{
		
		/* cria a lista de dados que vem do SQL*/
		JRBeanCollectionDataSource jrben = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, params, jrben);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}
}
