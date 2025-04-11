package com.example.travelagency.common;

// 커스텀 StringUtil
public class StringUtil {
    /**
     * 문자열이 null이거나 빈 문자열("")인지 확인합니다.
     *
     * @param str 확인할 문자열
     * @return true (null 또는 빈 문자열인 경우), false (그렇지 않은 경우)
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 문자열이 null이 아니거나 빈 문자열("")이 아닌지 확인합니다.
     *
     * @param str 확인할 문자열
     * @return true (null도 아니고 빈 문자열도 아닌 경우), false (그렇지 않은 경우)
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 문자열이 null, 빈 문자열(""), 또는 공백 문자(" ", "\t", "\n" 등)로만 이루어져 있는지 확인합니다.
     *
     * @param str 확인할 문자열
     * @return true (null, 빈 문자열, 또는 공백으로만 이루어진 경우), false (그렇지 않은 경우)
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 문자열이 null, 빈 문자열(""), 또는 공백 문자(" ", "\t", "\n" 등)로만 이루어져 있지 않은지 확인합니다.
     *
     * @param str 확인할 문자열
     * @return true (null도 아니고, 빈 문자열도 아니고, 공백으로만 이루어지지도 않은 경우), false (그렇지 않은 경우)
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 문자열 앞뒤의 공백 문자를 제거합니다 (null-safe).
     *
     * @param str 공백을 제거할 문자열
     * @return 앞뒤 공백이 제거된 문자열 (null 입력 시 null 반환)
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 문자열을 지정된 구분자로 연결합니다.
     *
     * @param delimiter 구분자
     * @param elements  연결할 문자열 배열
     * @return 연결된 문자열 (배열이 null이거나 비어있으면 빈 문자열 반환)
     */
    public static String join(CharSequence delimiter, CharSequence... elements) {
        if (elements == null || elements.length == 0) {
            return "";
        }
        return String.join(delimiter, elements);
    }

    /**
     * 문자열이 특정 접두사로 시작하는지 확인합니다 (null-safe).
     *
     * @param str    확인할 문자열
     * @param prefix 접두사
     * @return true (문자열이 접두사로 시작하는 경우), false (그렇지 않은 경우)
     */
    public static boolean startsWith(String str, String prefix) {
        return str != null && prefix != null && str.startsWith(prefix);
    }

    /**
     * 문자열이 특정 접미사로 끝나는지 확인합니다 (null-safe).
     *
     * @param str    확인할 문자열
     * @param suffix 접미사
     * @return true (문자열이 접미사로 끝나는 경우), false (그렇지 않은 경우)
     */
    public static boolean endsWith(String str, String suffix) {
        return str != null && suffix != null && str.endsWith(suffix);
    }

    /**
     * 문자열이 특정 문자열을 포함하는지 확인합니다 (null-safe).
     *
     * @param str       확인할 문자열
     * @param searchStr 찾을 문자열
     * @return true (문자열이 특정 문자열을 포함하는 경우), false (그렇지 않은 경우)
     */
    public static boolean contains(String str, String searchStr) {
        return str != null && searchStr != null && str.contains(searchStr);
    }

    /**
     * 문자열이 특정 문자열을 대소문자 구분 없이 포함하는지 확인합니다 (null-safe).
     *
     * @param str       확인할 문자열
     * @param searchStr 찾을 문자열
     * @return true (문자열이 특정 문자열을 대소문자 구분 없이 포함하는 경우), false (그렇지 않은 경우)
     */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        return str != null && searchStr != null && str.toLowerCase().contains(searchStr.toLowerCase());
    }

    /**
     * 문자열을 모두 소문자로 변환합니다 (null-safe).
     *
     * @param str 변환할 문자열
     * @return 소문자로 변환된 문자열 (null 입력 시 null 반환)
     */
    public static String toLowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    /**
     * 문자열을 모두 대문자로 변환합니다 (null-safe).
     *
     * @param str 변환할 문자열
     * @return 대문자로 변환된 문자열 (null 입력 시 null 반환)
     */
    public static String toUpperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    /**
     * 문자열의 첫 글자를 대문자로 변환합니다 (null 또는 빈 문자열 입력 시 그대로 반환).
     *
     * @param str 변환할 문자열
     * @return 첫 글자가 대문자로 변환된 문자열
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 문자열의 첫 글자를 소문자로 변환합니다 (null 또는 빈 문자열 입력 시 그대로 반환).
     *
     * @param str 변환할 문자열
     * @return 첫 글자가 소문자로 변환된 문자열
     */
    public static String uncapitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 문자열을 지정된 길이로 줄이고, 길이가 넘치면 "..."을 추가합니다 (null-safe).
     *
     * @param str     줄일 문자열
     * @param maxLength 최대 길이
     * @return 줄여진 문자열
     */
    public static String abbreviate(String str, int maxLength) {
        if (str == null) {
            return null;
        }
        if (str.length() <= maxLength) {
            return str;
        }
        if (maxLength < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * 문자열을 역순으로 뒤집습니다 (null-safe).
     *
     * @param str 뒤집을 문자열
     * @return 역순으로 된 문자열 (null 입력 시 null 반환)
     */
    public static String reverse(String str) {
        return str == null ? null : new StringBuilder(str).reverse().toString();
    }

    /**
     * 문자열에서 특정 문자열을 다른 문자열로 치환합니다 (null-safe).
     *
     * @param text        원본 문자열
     * @param searchString 찾을 문자열
     * @param replacement  치환할 문자열
     * @return 치환된 문자열 (원본 문자열이 null이면 null 반환)
     */
    public static String replace(String text, String searchString, String replacement) {
        if (text == null || isEmpty(searchString) || replacement == null) {
            return text;
        }
        return text.replace(searchString, replacement);
    }

    /**
     * 문자열에서 정규 표현식에 매칭되는 부분을 다른 문자열로 치환합니다 (null-safe).
     *
     * @param text        원본 문자열
     * @param regex       정규 표현식
     * @param replacement 치환할 문자열
     * @return 치환된 문자열 (원본 문자열이 null이면 null 반환)
     */
    public static String replaceAll(String text, String regex, String replacement) {
        return text == null ? null : text.replaceAll(regex, replacement);
    }

    /**
     * 문자열에서 정규 표현식에 매칭되는 첫 번째 부분을 다른 문자열로 치환합니다 (null-safe).
     *
     * @param text        원본 문자열
     * @param regex       정규 표현식
     * @param replacement 치환할 문자열
     * @return 치환된 문자열 (원본 문자열이 null이면 null 반환)
     */
    public static String replaceFirst(String text, String regex, String replacement) {
        return text == null ? null : text.replaceFirst(regex, replacement);
    }

    /**
     * 문자열을 지정된 길이로 채웁니다 (왼쪽 채움).
     *
     * @param str     채울 문자열
     * @param size    총 길이
     * @param padChar 채울 문자
     * @return 채워진 문자열 (str이 null이면 null 반환)
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        return String.valueOf(padChar).repeat(pads) + str;
    }

    /**
     * 문자열을 지정된 길이로 채웁니다 (오른쪽 채움).
     *
     * @param str     채울 문자열
     * @param size    총 길이
     * @param padChar 채울 문자
     * @return 채워진 문자열 (str이 null이면 null 반환)
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        return str + String.valueOf(padChar).repeat(pads);
    }
}
