INPUT=README.md
OUTPUT=talk_android_annotations.pdf
THEME=metropolis
LISTINGS=listings_setup.tex

$(OUTPUT): $(INPUT)
	pandoc -st beamer -V theme:$(THEME) $(INPUT) --listings -H $(LISTINGS) -o $(OUTPUT)

run: $(OUTPUT)
	impressive -t Crossfade --nologo $(OUTPUT)

clean:
	rm -f $(OUTPUT)
