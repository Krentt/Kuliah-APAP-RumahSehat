import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';
import 'package:rumah_sehat_app/widgets/tagihan_widget.dart';

class PasienTagihan extends StatefulWidget {
  static const route = "/tagihan/list";

  const PasienTagihan({Key? key}) : super(key: key);

  @override
  _PasienTagihanState createState() => _PasienTagihanState();
}

class _PasienTagihanState extends State<PasienTagihan> {
  bool isInit = true;
  bool isLoading = false;

  @override
  void didChangeDependencies() {
    if (isInit) {
      isLoading = true;
      Provider.of<TagihanProvider>(context, listen: false).inisialData().then((
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
        title: const Text("Bill List")
      ),

      /// Application Body (body)
      body: (isLoading)

        /// if isLoading = True, then show CircularProgressIndicator
        ? const Center(
          child: CircularProgressIndicator(),
        )

        /// If No Tagihan, show "No Data"
        : (prov.getAllTagihan.isEmpty)
          ? const Center(
            child: Text(
              "No Bills! Yay",
              style: TextStyle(
                fontSize: 25,
              ),
            ),
          )

        /// If Pasien has Tagihan, then
        /// Builds List of Tagihan Information
        /// TODO: Press to open Tagihan Details Page
          : ListView.builder(
        itemCount: prov.getAllTagihan.length,
        itemBuilder: (context, i) => TagihanWidget(
          prov.getAllTagihan[i].id,
          prov.getAllTagihan[i].kodeAppointment,
          prov.getAllTagihan[i].tanggalTerbuat,
          prov.getAllTagihan[i].tanggalBayar,
          prov.getAllTagihan[i].isPaid,
          prov.getAllTagihan[i].total,
        ),
      ),
    );
  }
}