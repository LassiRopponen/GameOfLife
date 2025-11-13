BUILDDIR=target
SOURCEDIR=src

all:
ifeq ($(OS),Windows_NT)
	if not exist $(BUILDDIR) mkdir $(BUILDDIR)
else
	mkdir -p $(BUILDDIR)
endif
	javac -d $(BUILDDIR) -cp $(SOURCEDIR) $(SOURCEDIR)/Main.java

clean:
ifeq ($(OS),Windows_NT)
	if exist $(BUILDDIR) rmdir /s /q $(BUILDDIR)
else
	rm -rf $(BUILDDIR)
endif
