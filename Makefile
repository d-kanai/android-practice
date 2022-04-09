IT:
	./gradlew connectedAndroidTest --info
cucumber:
	./gradlew connectedAndroidTest -Pcucumber --info
coverage:
	open app/build/reports/coverage/androidTest/debug/index.html
