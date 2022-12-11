import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';

import '../pages/tagihan_details.dart';

class TagihanWidget extends StatelessWidget {
  final String? id, kodeAppointment, tanggalTerbuat, tanggalBayar;
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
          ///Tap to see specific tagihan details
          Navigator.pushNamed(context, TagihanDetails.route, arguments: id);
        },
        leading: CircleAvatar(
          child: Padding(
            padding: const EdgeInsets.all(5),
            child: FittedBox(
              child: Text("$id", style: const TextStyle(fontWeight: FontWeight.bold),),
            ),
          ),
        ),
        /// Main Text
        title: Text("Tanggal Terbuat : $tanggalTerbuat \n"
                    "Total Biaya     : $total \n"),
        /// Mini Icon (Left Side)
        trailing: Icon(isPaid == true ? Icons.done_all : Icons.timelapse, color: isPaid == true? Colors.green : Colors.blueGrey,),
      );
  }

}