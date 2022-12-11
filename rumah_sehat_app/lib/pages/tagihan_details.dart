import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';

class TagihanDetails extends StatefulWidget {
  static const route = "/tagihan/detail";

  const TagihanDetails({Key? key}) : super(key: key);

  @override
  State<TagihanDetails> createState() => _TagihanDetailsState();
}

class _TagihanDetailsState extends State<TagihanDetails> {
  bool isInit = true;
  bool isLoading = false;


  // @override
  // void didChangeDependencies() {
  //   if (isInit) {
  //     isLoading = true;
  //     Provider.of<TagihanProvider>(context, listen: false).tagihanDetails().then((
  //         value) {
  //       setState(() {
  //         isLoading = false;
  //       });
  //     }).catchError(
  //           (err) {
  //         print(err);
  //         showDialog(
  //           context: context,
  //           builder: (context) {
  //             return AlertDialog(
  //               title: const Text("Error Occured"),
  //               content: Text(err.toString()),
  //               actions: [
  //                 TextButton(
  //                   onPressed: () {
  //                     setState(() {
  //                       isLoading = false;
  //                     });
  //                     Navigator.pop(context);
  //                   },
  //                   child: const Text("Okay"),
  //                 ),
  //               ],
  //             );
  //           },
  //         );
  //       },
  //     );
  //   }
  //   super.didChangeDependencies();
  // }

  /// Flutter Page Starts Here
  @override
  Widget build(BuildContext context) {
    String idTagihan = ModalRoute.of(context)?.settings.arguments as String;
    var prov = Provider.of<TagihanProvider>(context, listen: false);
    var selectedTagihan = prov.selectById(idTagihan);

    return Scaffold(
      /// Application Header (AppBar)
      appBar: AppBar(
        title: const Text("Tagihan Details"),
        elevation: 0.0,
        backgroundColor: Colors.blue,
        centerTitle: true,
        leading: GestureDetector(
          onTap: () {
            Navigator.pop(context);
          },
          child: const Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
        ),
      ),

      /// Application Body (body)
      body:

        /// if isLoading = True, then show CircularProgessIndicator
        (isLoading) ? const Center(
          child: CircularProgressIndicator(),
        )

        /// TODO: else show Tagihan Detail
        : Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              height: 200,
              decoration: BoxDecoration(
                  color: Colors.blue,
                  borderRadius: BorderRadius.only(bottomLeft: Radius.circular(30), bottomRight: Radius.circular(30))
              ),
              child: Container(
                margin: EdgeInsets.only(bottom: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Container(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Kode",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Dokter",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Waktu",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Status",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Container(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(selectedTagihan.kodeAppointment.toString(),
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 22,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(DateFormat("dd-MM-yyyy HH:mm").format(selectedTagihan.tanggalTerbuat as DateTime),
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(DateFormat("dd-MM-yyyy HH:mm").format(selectedTagihan.tanggalBayar as DateTime),
                              style: TextStyle(
                                color: Colors.yellow,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(selectedTagihan.isPaid == true? "SELESAI" : "BELUM SELESAI" ,
                              style: TextStyle(
                                color: selectedTagihan.isPaid == true? Colors.lightGreen : Colors.amber,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(left: 20, top: 30),
              child: Text('Deskripsi',
                style: TextStyle(
                  color: Color(0xff363636),
                  fontSize: 25,
                  fontFamily: 'Roboto',
                  fontWeight: FontWeight.w700,
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(left: 20, top: 10, right: 20),
              child: Text('Appointment merupakan sesi konsultasi Anda '
                  ' dengan dokter sehingga dimohon datang tepat waktu. '
                  'Sesi konsultasi ini kurang lebih akan berlangsung '
                  'selama satu jam.',
                style: TextStyle(
                  color: Color(0xff363636),
                  fontSize: 18,
                  fontFamily: 'Roboto',
                ),
                textAlign: TextAlign.justify,
              ),
            ),
            SizedBox(height: 20),
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(10),
              margin: const EdgeInsets.only(bottom: 30),
              child: selectedTagihan.kodeAppointment != null ? ElevatedButton(
                onPressed: () {},
                child: Text(
                  "Detail Resep",
                  style: TextStyle(
                    fontSize: 18,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  padding: EdgeInsets.symmetric(vertical: 15),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(100),
                  ),
                ),
              ) : Text("TIDAK ADA RESEP",
                style: TextStyle(
                    color: Colors.blue,
                    fontSize:20,
                    fontFamily: 'Roboto',
                    fontWeight: FontWeight.bold
                ),
                textAlign: TextAlign.center,
              ),
            ),
          ],
        ),
    );
  }
}