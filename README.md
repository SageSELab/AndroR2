
# AndroR2: A Dataset of Manually-Reproduced Bug Reports for Android apps
* Reproducible bug reports are located in the `reports` folder.
* Reproduction metadata is located in the `metadata` folder and encoded in JSON files.
* The relevant APKs are in the `apks` folder. 
* The reproduction scripts are located in the `scripts` folder [here](scripts/reproducing_script_project/app/src/androidTest/java/edu/sage/android) and the instructions for running the scripts are also located in the `scripts` folder [here](scripts).
* The code utilized for data collection can be found in the `code` folder.
* A link to access the databse of 82,455 issues can be found in `database`.





## Reproducible Bug Reports

* *ID* = identifier of the bug report in this dataset
* *GitHub Issue* = link to the bug report on GitHub
* *Commit ID* = commit ID identifying the app version used to reproduce the bug report
* *Android OS Version* = Android version used to reproduce the bug report
* *Failure Type* = type of failure associated with the bug report. *crash* identifies a crash in the app, *output* represents an error in the output of the app, *gui* identifies an error in the properties of the GUI of the app
* *S2Rs* = number of textual S2Rs in the bug report
* *GUI Actions* = number of GUI actions necessary to reproduce the failure described in the bug report
* *Setup GUI Actions* = setup GUI actions (SGAs). Number of GUI actions that need to be performed before the first GUI action mentioned in the S2Rs can be performed
* *RS GUI actions* = report-specific GUI actions (RSGAs). Number of GUI actions that are necessary to reproduce the bug reports starting from the first the GUI action associated with the first S2R 
* *RS Missing GUI Actions* = report-specific and missing GUI actions. Number of GUI actions that are not mentioned by the S2Rs but are necessary to reproduce the failure described in the bug report 
* *Multiple GUI Action S2Rs* = multiple-GUI-action S2Rs (MGAS2Rs). S2Rs that lead to execution of multiple GUI actions.
* *GUI Actions in MGAS2Rs* = number of GUI actions in multiple-GUI-action S2Rs.
* *SGAs in Android OS* = number of setup GUI actions that are performed in the Android OS
* *SGAs Outside App* = number of setup GUI actions that are performed outside the app and are not in the Android OS
* *RSGAs in the Android OS* = number of report-specific GUI actions that are performed in the Android OS
* *RSGAs Outside App* = number of report-specific GUI actions that are performed outside the app and are not in the Android OS
* *Reproducing Script* = C if the script contains all the GUI action, P if the script does not contain some of the actions.

| ID | Github Issue | Commit ID | Android OS Version | Failure Type | S2Rs | GUI Actions | Setup GUI Actions | RS GUI Actions |  RS Missing GUI Actions | Multiple GUI Action S2Rs | GUI Actions in MGAS2Rs | SGAs in Android OS | SGAs Outside App | RSGAs in Android OS | RSGAs Outside App| Reproducing Script |
| ---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|2|[Link](https://github.com/zwieback/FamilyFinance/issues/1)|d365188d7c7b9d9eb0021e73e0c6dc37aba79a3f|6.1|crash|6|6|0|6|0|0|0|1|0|0|0|C|
|7|[Link](https://github.com/vbier/habpanelviewer/issues/25)|2adbfce4039a6501bf2512a765ced223c53da315|8.1|crash|2|5|4|1|0|0|0|0|0|0|0|C|
|8|[Link](https://github.com/koelleChristian/trickytripper/issues/42)|7ef227ada3454529c7def79e41f48f6fa7b7463c|6|crash|5|10|5|5|0|1|2|0|0|0|0|C|
|10|[Link](https://github.com/zhanghai/MaterialFiles/issues/184)|003408bb61478c32f3119f7cb5e301db44caa199|6|output|5|10|1|9|3|2|2|0|0|0|0|C|
|11|[Link](https://github.com/gauravjot/android-noad-music-player/issues/1)|d3ab226c2d0e67b597a4d2cde1d652ceff6c1f52|6.1|crash|4|2|0|2|0|0|0|0|0|0|0|C|
|18|[Link](https://github.com/citiususc/calendula/issues/134)|6f0dcf6070cd132eba51824ebbfa743eb22b0568|8|crash|2|18|14|4|0|1|3|0|0|0|0|C|
|19|[Link](https://github.com/streetcomplete/StreetComplete/issues/1093)|8a2cb5df10f4aea90c79a2d111c989ae1830544a|6.0.1|gui|3|5|1|4|0|1|2|0|0|0|0|P|
|21|[Link](https://github.com/netmackan/ATimeTracker/issues/121)|394ccfc0c04223a263411e8af4487458e848d319|8.0.0|gui|3|7|1|6|0|1|4|0|0|0|0|C|
|22|[Link](https://github.com/netmackan/ATimeTracker/issues/124)|394ccfc0c04223a263411e8af4487458e848d319|7.1.1|gui|3|10|6|4|0|1|2|0|0|0|0|C|
|23|[Link](https://github.com/SecUSo/privacy-friendly-weather/issues/61)|de7d145b82ae5e06991ba1b9a598d8ec6647f1f9|7|crash|4|4|3|1|0|0|0|0|0|0|0|C|
|27|[Link](https://github.com/fossasia/phimpme-android/issues/1837)|85050f3b1cefe5f84dcad514efc884a430801139|7.1.1|gui|3|7|5|2|0|0|0|0|0|0|0|P|
|35|[Link](https://github.com/ccrama/Slide/issues/2773)|e1c5681e628efd895d6f513047d94b618c00a144|8|output|4|21|12|9|0|2|7|1|0|0|0|C|
|43|[Link](https://github.com/federicoiosue/Omni-Notes/issues/592)|5cc16b32eb5e65e6f8381d7ca097a3a8aabea57e|7.1.2|crash|2|31|28|3|1|1|0|1|0|0|0|C|
|44|[Link](https://github.com/federicoiosue/Omni-Notes/issues/634)|8d09f7ab504d520f9c456c2a12a5aa0067be0d90|9.0.0|output|3|18|7|11|5|2|5|0|0|0|0|C|
|45|[Link](https://github.com/federicoiosue/Omni-Notes/issues/764)|68d074acc7e3e8acda7709b88f007820e81923cd|8.1.0|output|7|19|7|12|1|0|0|0|0|0|0|C|
|50|[Link](https://github.com/asuc-octo/berkeley-mobile-android/issues/82)|d61672a19c538250f305352d35dace108c3594cd|9|crash|1|1|0|1|0|0|0|0|0|0|0|C|
|51|[Link](https://github.com/alexstyl/Memento-Calendar/issues/168)|2542c42b677b77620d170bb89bc68da767b4d65e|7|output|3|10|4|6|1|2|4|1|0|1|0|C|
|53|[Link](https://github.com/gsantner/markor/issues/1020)|73c73f21ffecbdec2d35bae8e0472980572af8e5|3|output|6|15|7|8|3|1|3|1|1|1|0|C|
|54|[Link](https://github.com/gsantner/markor/issues/331)|1a5478a490eb569551f4e30f2d668e28a9f95559|8|output|6|10|4|6|1|1|2|1|0|1|0|C|
|55|[Link](https://github.com/PhenoApps/Field-Book/issues/131)|395e94bf62312dfd3df66fdbad75412aacf733a2|10|gui|3|26|6|20|0|3|20|1|0|0|0|C|
|56|[Link](https://github.com/PhenoApps/Field-Book/issues/137)|68912f28ed6408d65198fecbb80e16e75d1c3532|10|output|4|18|6|12|0|3|12|1|0|0|0|C|
|62|[Link](https://github.com/Swati4star/Images-to-PDF/issues/85)|e5ec9ddbf335cffb226f4d82fac4f48b6c756667|8|output|5|16|2|14|4|4|10|1|0|1|0|C|
|71|[Link](https://github.com/Neamar/KISS/issues/1481)|3102b83a3c6208f7ba36a235d71254f1c8c8a9ff|6.1|gui|5|5|0|5|0|0|0|0|1|1|0|P|
|73|[Link](https://github.com/AntennaPod/AntennaPod/issues/2992)|d26d2126072654ba95f31f2bb638479bc2f199c4|8|gui|3|9|6|3|0|1|2|0|0|0|0|C|
|76|[Link](https://github.com/trebleshot/android/issues/79)|bea5ce3bce36cccf5a34d833ac6d8fc2e237984d|Fire OS 5.2.6.9|gui|3|6|6|0|6|0|0|0|0|0|0|C|
|77|[Link](https://github.com/CypherpunkArmory/UserLAnd/issues/753)|0dc015cf230c5268c4945188b701d56c0f99eff6|7|output|5|7|1|6|0|1|1|0|0|0|0|P|
|84|[Link](https://github.com/ultrasonic/ultrasonic/issues/187)|95496eb1200e892600b60d5048248e893b339af3|4.4.4 API 19|output|8|8|0|8|0|0|0|0|0|0|0|P|
|87|[Link](https://github.com/ultrasonic/ultrasonic/issues/203)|9c117386ba979be405b1a5cca960947a81012b25|7|output|2|2|0|2|0|0|0|0|0|0|0|P|
|89|[Link](https://github.com/VREMSoftwareDevelopment/WiFiAnalyzer/issues/164)|c57733f132ad9abc38622100d5864a82e1f84870|8.1.0|output|1|2|1|1|0|0|0|1|0|0|0|P|
|91|[Link](https://github.com/VREMSoftwareDevelopment/WiFiAnalyzer/issues/191)|b5433c0e9a7e86f234915d409abe2d46c62363bb|7|output|2|3|1|2|1|0|0|0|0|0|0|C|
|92|[Link](https://github.com/VREMSoftwareDevelopment/WiFiAnalyzer/issues/222)|cffde47fb60e1f030e0ba1665813af91ca057976|6.0.1|output|4|6|1|5|1|1|1|0|0|0|0|P|
|96|[Link](https://github.com/barbeau/gpstest/issues/404)|3715e4fed61d0f5d28ff9fc91919f3381c98e551|10|output|4|11|8|3|0|1|2|0|0|0|0|P|
|97|[Link](https://github.com/osmdroid/osmdroid/issues/1030)| 4ef81ae0d68bd3f062f4c763f9653300bc3ef2f1|6.0.1|crash|7|10|6|4|0|2|4|0|0|0|0|C|
|101|[Link](https://github.com/osmdroid/osmdroid/issues/999)|4ef81ae0d68bd3f062f4c763f9653300bc3ef2f1|6.0.1|gui|4|8|6|2|0|0|0|0|0|1|0|C|
|106|[Link](https://github.com/AdrienPoupa/VinylMusicPlayer/issues/139)|b18e0559b9fdd8eabd8504eaa4927708e7b80216|8|output|2|5|2|3|1|1|2|0|0|0|0|C|
|107|[Link](https://github.com/AdrienPoupa/VinylMusicPlayer/issues/149)|deac585a029850463ea92a5c5291c5f45d0b6518|9|gui|3|6|2|4|0|1|4|0|0|0|0|C|
|110|[Link](https://github.com/AdrienPoupa/VinylMusicPlayer/issues/311)|9971a70093f6b0c2c325ae5afda91c30fe96e54f|11|gui|3|6|2|4|0|1|4|0|1|0|0|C|
|117|[Link](https://github.com/openfoodfacts/openfoodfacts-androidapp/issues/2087)|fb45dce67c0756eb710a776ea940e243e21b94e6|8.1|gui|2|10|4|6|0|1|5|0|0|0|0|C|
|121|[Link](https://github.com/Automattic/simplenote-android/issues/1046)|ec7a257fba6e0ba29a793eeaebb1179aaf4f343f|9|gui|12|15|6|9|0|0|0|0|0|0|0|C|
|122|[Link](https://github.com/Automattic/simplenote-android/issues/613)|bf39d2825b5ff831ee515570e06bfd1afc95d13d|8|gui|3|16|8|8|5|3|8|0|0|0|0|C|
|123|[Link](https://github.com/Automattic/simplenote-android/issues/615)|bf39d2825b5ff831ee515570e06bfd1afc95d13d|9|output|2|20|8|12|6|1|4|0|0|1|0|C|
|125|[Link](https://github.com/Automattic/simplenote-android/issues/641)|118fd15d4ca830ce1953f079cc20b25020856546|8|gui|4|16|8|8|5|2|7|0|0|0|0|C|
|128|[Link](https://github.com/andOTP/andOTP/issues/567)|30b0b5747e728b9515ac36ca9c471c824854aaf1|6|output|2|13|9|4|4|0|0|0|0|0|0|C|
|129|[Link](https://github.com/andOTP/andOTP/issues/500)|b8b9834f06cf28686be5874923607f677b094df6|9|crash|3|19|9|10|0|2|9|0|0|0|0|C|
|130|[Link](https://github.com/andOTP/andOTP/issues/486)|7c34318c407c4677d3afdf27bdeb998244759720|8|gui|2|2|2|0|0|0|0|0|0|0|0|C|
|132|[Link](https://github.com/commons-app/apps-android-commons/issues/1697)|d7b956e5ac8d4ea3b590a929a8176975afd86422|7|output|4|15|8|7|1|1|4|1|1|0|1|C|
|135|[Link](https://github.com/commons-app/apps-android-commons/issues/2088)|83d1d1ee402dbd680192691a777aa3e4b10e5c55|8.1.0|output|4|3|0|3|0|0|0|0|0|0|0|C|
|137|[Link](https://github.com/kiwix/kiwix-android/issues/1414)|9709dfaeee4cd9e2d6a51a2d44de67682b3811d6|8|crash|4|4|0|4|0|0|0|0|0|0|0|C|
|139|[Link](https://github.com/kiwix/kiwix-android/issues/555)|e8f99a323b82a0323eb3ffcff8907429797522ab|5|gui|2|4|0|4|0|2|4|0|0|0|0|C|
|142|[Link](https://github.com/moezbhatti/qksms/issues/1124)|4e4a72cafdba463b571610a0ccd401153594dd71|6.0.1|output|3|6|1|5|0|2|5|0|0|0|0|C|
|144|[Link](https://github.com/moezbhatti/qksms/issues/1155)|71deb2dfd033d940aa7d91794322618ba39abf4d|sdk v27|gui|3|9|6|3|0|0|0|0|0|0|1|C|
|145|[Link](https://github.com/moezbhatti/qksms/issues/1179)|3fbf6dfa3ae8f536c3b6d6537d5cb1133634d59c|6.0.1|gui|3|7|0|7|2|2|6|0|0|0|0|C|
|151|[Link](https://github.com/beemdevelopment/Aegis/issues/200)|cc55a6dacb02cde5528fbab400de716ca3b10c11|7|gui|3|9|6|3|0|0|0|0|0|0|0|C|
|152|[Link](https://github.com/beemdevelopment/Aegis/issues/287)|10c206270a567052acd13f48e484332bb02e3a05|7|output|2|13|3|10|0|1|4|0|0|0|0|C|
|155|[Link](https://github.com/beemdevelopment/Aegis/issues/473)|6e54497492cf0b8329f423bae5e1879b2ede4fd9|9|output|3|9|3|6|0|1|4|0|0|0|0|C|
|157|[Link](https://github.com/ankidroid/Anki-Android/issues/5753)|679229d7c8c5e9a4f9e1425ddf521d7d83ae08a5|10|output|2|8|2|6|0|2|6|1|0|0|0|C|
|158|[Link](https://github.com/ankidroid/Anki-Android/issues/5127)|5c7ef543d2ac9233ba6818e43b1d1a097f7332a8|11|gui|3|10|7|3|1|1|2|1|0|0|0|C|
|159|[Link](https://github.com/ankidroid/Anki-Android/issues/6432)|2948dfbb88bf535a6099245dfd5bc1432f9c8914|11|crash|3|37|2|35|0|3|35|1|0|0|0|C|
|160|[Link](https://github.com/ankidroid/Anki-Android/issues/6119)|abd1db25de96a2f11fb08291e56aa6728bd7de61|11|gui|3|14|8|6|0|1|4|1|0|0|0|C|
|162|[Link](https://github.com/k9mail/k-9/issues/3255)|6e392f62b78689f7a05bdb728ce81955dcebb8e1|6|crash|4|6|1|5|0|1|5|1|0|0|0|C|
|164|[Link](https://github.com/k9mail/k-9/issues/3971)|6ff1b67a7f1f5b270eb7c8c7e0b60c9ba47f2d33|7|gui|3|7|1|6|0|1|6|1|0|0|0|C|
|165|[Link](https://github.com/k9mail/k-9/issues/4435)|a903bd6f81e929bdd486b12e24afe851d06b7dcf|10|output|3|28|23|5|0|2|4|1|1|1|0|C|
|168|[Link](https://github.com/cgeo/cgeo/issues/7369)|31240dd2fc63d91aef6fd71a4e09b50d1d169599|10|crash|3|3|2|1|0|0|0|1|0|1|0|C|
|174|[Link](https://github.com/mozilla-mobile/focus-android/issues/2499)|892b1ee0cfdbdd747a37e413e205258f46970972|10|crash|3|6|1|5|1|2|4|1|0|1|0|C|
|178|[Link](https://github.com/SecUSo/privacy-friendly-pedometer/issues/28)|1d950d187fae89396ce6b1d0f78f6e44dd095440|4.4.2|crash|1|1|0|1|0|0|0|0|0|0|0|C|
|186|[Link](https://github.com/longdivision/hex/issues/9)|dbed34ce37ffda535833cccd81b4d576961038b1|8|crash|4|2|0|2|0|0|0|1|0|0|0|C|
|188|[Link](https://github.com/hidroh/materialistic/issues/565)|204365adfb400b8ef098c4d9fa4f6852b9b4eb98|8|gui|4|6|0|6|2|2|4|1|0|0|0|C|
|191|[Link](https://github.com/reloZid/android-anuto/issues/68)|6e925f1e60796c2f1623a1514e9d9192ab3bfd1e|8|output|2|2|0|2|0|0|0|1|0|0|0|C|
|192|[Link](https://github.com/gsantner/markor/issues/162)|7214fc661e79f0a9cb18344a2be14d169f212889|8|gui|3|12|8|4|0|1|2|1|0|1|0|C|
|193|[Link](https://github.com/alexstyl/Memento-Calendar/issues/7)|a6cd4f2a11b78135e4d9884fc6b18c1d96a8ca94|8|gui|6|5|0|5|0|0|0|1|0|0|0|C|
|198|[Link](https://github.com/y20k/transistor/issues/149)|5a84332a1679f179777404ccd541e00541512efb|8|crash|4|12|0|12|0|2|9|1|0|0|0|C|
|199|[Link](https://github.com/inaturalist/iNaturalistAndroid/issues/219)|64d685e530715186fdb4cfecfce01ee79328f166|7|output|4|15|15|11|2|1|1|0|0|0|0|C|
|200|[Link](https://github.com/inaturalist/iNaturalistAndroid/issues/237)|10337dac94ee9631ec560718a8d85c033681b23d|8|output|3|10|6|3|0|0|0|1|0|0|0|C|
|201|[Link](https://github.com/inaturalist/iNaturalistAndroid/issues/302)|1ae5ed05e9b4c5927db5a4daa9307695beb8c286|7|gui|3|12|9|9|2|1|1|0|0|0|0|C|
|205|[Link](https://github.com/mozilla-mobile/focus-android/issues/4102)|a8646bb730cced3bf552297d5560ea2c12d3b77e|8|output|4|9|1|8|4|3|7|1|0|1|0|C|
|206|[Link](https://github.com/codinguser/gnucash-android/issues/615)|d1c31033366ae7044e4b115b9ac021060660ef18|8|output|2|11|8|3|1|1|2|1|0|1|0|C|
|208|[Link](https://github.com/codinguser/gnucash-android/issues/633)|d1c31033366ae7044e4b115b9ac021060660ef18|8|crash|2|12|8|4|2|0|0|1|0|1|0|C|
|209|[Link](https://github.com/codinguser/gnucash-android/issues/689)|52f72970eada423f10ed11359f628c5755545b24|8|gui|5|12|7|5|3|0|0|1|0|1|0|C|
|217|[Link](https://github.com/mozilla-mobile/focus-android/issues/3287)|9e11cc15cc6f7c8636f4fd3e0f9e8578811949d9|7|gui|2|7|5|2|0|0|0|0|0|0|0|C|
|226|[Link](https://github.com/AdrienPoupa/VinylMusicPlayer/issues/218)|89a045c612ab6b130572697a3e2e827c6277d2ee|9|output|5|8|2|6|0|1|3|0|0|0|0|C|
|227|[Link](https://github.com/codinguser/gnucash-android/issues/723)|3c26b278edd14dd909894bbe4346965e77013245|7.1|gui|5|31|7|24|19|3|22|0|0|0|0|C|
|228|[Link](https://github.com/codinguser/gnucash-android/issues/731)|bcdab71c58f2ade300aa4cea23f0c9f728f26d88|4.2.2|output|1|13|10|3|0|0|0|0|0|0|0|P|
|248|[Link](https://github.com/getodk/collect/issues/2958)|b4cb65b22c26c071734ede460adf4e6d001edbcd|9|gui|5|30|1|29|0|1|23|0|0|0|0|C|
|250|[Link](https://github.com/moezbhatti/qksms/issues/612)|0f39237e8e00957827762642b0c19347578ca421|6|gui|5|9|1|8|0|0|0|0|0|0|0|C|
|253|[Link](https://github.com/mozilla-mobile/focus-android/issues/3932)|fc44c9ee767d747699b0e80883974e14e9bd5a29|10|crash|2|5|1|4|0|1|3|0|0|0|0|C|
|256|[Link](https://github.com/codinguser/gnucash-android/issues/654)|5a644f09aae5f7017369e69720486910082da11a|7.1|crash|5|22|7|15|11|3|14|0|0|0|0|C|
|266|[Link](https://github.com/mozilla-mobile/focus-android/issues/3892)|fc44c9ee767d747699b0e80883974e14e9bd5a29|10|output|6|12|8|4|0|0|0|1|0|0|0|C|
|271|[Link](https://github.com/beemdevelopment/Aegis/issues/500)|6e54497492cf0b8329f423bae5e1879b2ede4fd9|10|crash|2|12|4|8|0|2|8|0|0|0|0|P|
|272|[Link](https://github.com/mozilla-mobile/focus-android/issues/3297)|9e11cc15cc6f7c8636f4fd3e0f9e8578811949d9|10|output|6|9|1|8|0|1|4|0|0|0|0|C|
|273|[Link](https://github.com/ankidroid/Anki-Android/issues/5688)|6d006a30219a3a1b42dd835119d08cb797ed6203|10|gui|3|15|8|7|4|0|0|1|0|0|0|C|
