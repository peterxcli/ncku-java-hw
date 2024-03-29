STUDENT_ID=F74114752
TARGET=src META-INF

zip:
ifndef HW
	$(error HW is not set)
endif
	cd $(HW) && zip -r $(STUDENT_ID).zip $(TARGET) && mv $(STUDENT_ID).zip ../
.PHONY: zip

judge: zip
	./upload.sh $(STUDENT_ID) $(HW)
.PHONY: judge
