package geteway.service;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail() {

        try{
            String markdown = """
        # Daftar Produk

        Berikut ini adalah daftar produk dan harganya:

        | No | Nama Produk      | Harga     | Stok |
        |----|------------------|-----------|------|
        | 1  | Buku Tulis       | Rp5.000   | 100  |
        | 2  | Pensil           | Rp2.000   | 150  |
        | 3  | Penghapus        | Rp1.500   | 75   |
        | 4  | Penggaris 30cm   | Rp4.000   | 50   |

        **Catatan:** Harga dapat berubah sewaktu-waktu.
        """;

            MutableDataSet options = new MutableDataSet();
            options.set(Parser.EXTENSIONS, List.of(TablesExtension.create()));

            Parser parser = Parser.builder(options).build();
            HtmlRenderer renderer = HtmlRenderer.builder(options).build();
            Node document = parser.parse(markdown);
            String htmlContent = renderer.render(document)
                    .replace("<table>", "<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">")
                    .replace("<th>", "<th style=\"border: 1px solid #000000; padding: 8px; text-align:center; \" >")
                    .replace("<td>", "<td style=\"border: 1px solid #000000; padding: 8px; text-align:center;\" >");

            log.info("Res := {} " , htmlContent);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("mikhael.wellman@idstar.group");
            helper.setTo("mikhaelwellman423@gmail.com");
            helper.setSubject("Contoh Email");
            helper.setText(htmlContent, true); // true = HTML

            javaMailSender.send(mimeMessage);
            log.info("Email terkirim!");

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
