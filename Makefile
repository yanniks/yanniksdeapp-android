all:
	./gradlew build
clean:
	./gradlew clean
androidpit:
	rm -f app/src/main/res/values/store.xml
	echo '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n<string name="stu" translatable="false">androidpit</string>\n</resources>' > app/src/main/res/values/store.xml
googleplay:
	rm -f app/src/main/res/values/store.xml
	echo '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n<string name="stu" translatable="false">play</string>\n</resources>' > app/src/main/res/values/store.xml
amazon:
	rm -f app/src/main/res/values/store.xml
	echo '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n<string name="stu" translatable="false">amazon</string>\n</resources>' > app/src/main/res/values/store.xml
androidpitbuild: androidpit all
googleplaybuild: googleplay all
amazonbuild: amazon all
install:
	adb install -r app/build/outputs/apk/app-debug.apk
release:
	rm -f app/src/main/res/values/store.xml
	echo '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n<string name="stu" translatable="false">androidpit</string>\n</resources>' > app/src/main/res/values/store.xml
	./gradlew assembleRelease
	mkdir -p release
	mv app/app-release.apk release/app-AndroidPit.apk
	rm -f app/src/main/res/values/store.xml
	echo '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n<string name="stu" translatable="false">play</string>\n</resources>' > app/src/main/res/values/store.xml
	./gradlew assembleRelease
	mv app/app-release.apk release/app-GooglePlay.apk
	rm -f app/src/main/res/values/store.xml
	echo '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n<string name="stu" translatable="false">amazon</string>\n</resources>' > app/src/main/res/values/store.xml
	./gradlew assembleRelease
	mv app/app-release.apk release/app-Amazon.apk