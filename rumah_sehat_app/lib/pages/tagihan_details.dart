import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/pasien_saldo.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';

import '../widgets/tagihan_widget.dart';

class TagihanDetails extends StatefulWidget {
  static const route = "/tagihan/{id}/detail";

  const TagihanDetails({Key? key}) : super(key: key);

  @override
  _TagihanDetailsState createState() => _TagihanDetailsState();
}

class _TagihanDetailsState extends State<TagihanDetails> {
  bool isInit = true;
  bool isLoading = false;


  @override
  void didChangeDependencies() {
    if (isInit) {
      isLoading = true;
      Provider.of<TagihanProvider>(context, listen: false).tagihanDetails().then((
          value) {
        setState(() {
          isLoading = false;
        });
      }).catchError(
            (err) {
          print(err);
          showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: const Text("Error Occured"),
                content: Text(err.toString()),
                actions: [
                  TextButton(
                    onPressed: () {
                      setState(() {
                        isLoading = false;
                      });
                      Navigator.pop(context);
                    },
                    child: const Text("Okay"),
                  ),
                ],
              );
            },
          );
        },
      );
    }
    super.didChangeDependencies();
  }

  /// Flutter Page Starts Here
  @override
  Widget build(BuildContext context) {
    final prov = Provider.of<TagihanProvider>(context);
    return Scaffold(

      /// Application Header (AppBar)
      appBar: AppBar(
        title: const Text("Tagihan Details"),
      ),

      /// Application Body (body)
      body:

        /// if isLoading = True, then show CircularProgessIndicator
        (isLoading) ? const Center(
          child: CircularProgressIndicator(),
        )

        /// TODO: else show Tagihan Detail
        : (prov.getAllTagihan.isEmpty)
          ? const Center(
            child: Text(
              "No Data",
              style: TextStyle(
                fontSize: 25,
              ),
            ),
          )


        /// Builds List of Tagihan Information
          : ListView.builder(
        itemBuilder: (context, i) => TagihanWidget(
          prov.getAllTagihan[0].id,
          prov.getAllTagihan[0].kodeAppointment,
          prov.getAllTagihan[0].tanggalTerbuat,
          prov.getAllTagihan[0].tanggalBayar,
          prov.getAllTagihan[0].isPaid,
          prov.getAllTagihan[0].total,
        ),
      ),

    );
  }
}