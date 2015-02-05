import os


def main():
    f = open("MANIFEST.MF")
    f.readline()

    s = ""
    s += f.readline()
    s = s[12:-1]
    for i in range(33):
        s += f.readline()[1:-1]

    a = s.split(" ")
    r = "%REPO%\\"
    o = ""
    for i in a:
        o = o + r + i + ";"
    print o

if __name__ == "__main__":
    main()
