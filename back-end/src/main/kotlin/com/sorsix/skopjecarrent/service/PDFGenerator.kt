package com.sorsix.skopjecarrent.service

import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.sorsix.skopjecarrent.model.Reservation
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.time.Duration
import java.time.format.DateTimeFormatter
import java.util.stream.Stream


object PDFGenerator {
    private val logger = LoggerFactory.getLogger(PDFGenerator::class.java)
    fun PDFReport(reservation: Reservation): ByteArrayInputStream {
        val document = Document()
        val out = ByteArrayOutputStream()
        try {
            PdfWriter.getInstance(document, out)
            document.open()

            val font = FontFactory.getFont(
                FontFactory.HELVETICA, 14f,
                BaseColor.BLACK
            )
            val para = Paragraph("Skopje Car Rent Payment Details", font)
            para.alignment = Element.ALIGN_CENTER
            document.add(para)
            document.add(Chunk.NEWLINE)
            val table = PdfPTable(5)
            // Add PDF Table Header ->
            Stream.of("Car model", "Seats", "Trasmission", "Air Conditioner", "Insurance").forEach { headerTitle: String? ->
                val header = PdfPCell()
                val headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD)
                header.backgroundColor = BaseColor.LIGHT_GRAY
                header.horizontalAlignment = Element.ALIGN_CENTER
                header.borderWidth = 2f
                header.phrase = Phrase(headerTitle, headFont)
                table.addCell(header)
            }

            val idCell = PdfPCell(Phrase(reservation.car.model))
            idCell.paddingLeft = 4f
            idCell.verticalAlignment = Element.ALIGN_MIDDLE
            idCell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(idCell)
            val seatsCell = PdfPCell(Phrase(reservation.car.seats.toString()))
            seatsCell.paddingLeft = 4f
            seatsCell.verticalAlignment = Element.ALIGN_MIDDLE
            seatsCell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(seatsCell)
            val transmissionCell = PdfPCell(Phrase(reservation.car.transmission.toString()))
            transmissionCell.verticalAlignment = Element.ALIGN_MIDDLE
            transmissionCell.horizontalAlignment = Element.ALIGN_CENTER
            transmissionCell.paddingRight = 4f
            table.addCell(transmissionCell)
            val conditionerCell = PdfPCell(Phrase(reservation.car.withAirConditioner.toString()))
            conditionerCell.verticalAlignment = Element.ALIGN_MIDDLE
            conditionerCell.horizontalAlignment = Element.ALIGN_CENTER
            conditionerCell.paddingRight = 4f
            table.addCell(conditionerCell)
            val insuranceCell = PdfPCell(Phrase(reservation.car.withInsurance.toString()))
            insuranceCell.verticalAlignment = Element.ALIGN_MIDDLE
            insuranceCell.horizontalAlignment = Element.ALIGN_CENTER
            insuranceCell.paddingRight = 4f
            table.addCell(insuranceCell)

            document.add(table)
            document.add(Chunk.NEWLINE)

            val totalPrice = (Duration.between(reservation.pickUpDate, reservation.dropOffDate).toDays())*reservation.car.priceForADay
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            val pickUpDate = formatter.format(reservation.pickUpDate)
            val dropOffDate = formatter.format(reservation.dropOffDate)
            document.add(Paragraph("Client ${reservation.client.name} ${reservation.client.surname} with e-mail address ${reservation.client.email}"))
            document.add(Paragraph("The car is requested from ${pickUpDate} at location ${reservation.pickUpLocation.name} to  ${dropOffDate} at location ${reservation.dropOffLocation.name}"))
            document.add(Paragraph("Total price for the service is ${totalPrice} EUR"))
            document.close()
        } catch (e: DocumentException) {
            logger.error(e.toString())
        }
        return ByteArrayInputStream(out.toByteArray())
    }
}