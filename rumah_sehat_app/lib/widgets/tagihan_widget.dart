import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/detail_appointment_page.dart';
import 'package:rumah_sehat_app/providers/appointment.dart';
import 'package:rumah_sehat_app/providers/pasien.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';

class TagihanWidget extends StatelessWidget {
  final String? id, kodeAppointment;
  final String? tanggalTerbuat, tanggalBayar;
  final bool? isPaid;
  final int? total;

  const TagihanWidget(
      this.id, this.kodeAppointment, this.tanggalTerbuat, this.tanggalBayar,
      this.isPaid, this.total,
      {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
      var prov = Provider.of<TagihanProvider>(context, listen: false);
      return ListTile(
        onTap: () {
          // Navigator.pushNamed(context, DetailAppointmentPage.route, arguments: kode);
        },
        leading: CircleAvatar(
          child: Padding(
            padding: const EdgeInsets.all(5),
            child: FittedBox(
              child: Text("$kodeAppointment", style: const TextStyle(fontWeight: FontWeight.bold),),
            ),
          ),
        ),
        title: Text("Tanggal Terbuat : $tanggalTerbuat \n"
                    "Total Biaya     : $total \n"
                    "Status          : $isPaid"),
        // trailing: Icon(isDone == true ? Icons.done_all : Icons.timelapse, color: isDone == true? Colors.green : Colors.blueGrey,),
      );
  }

}