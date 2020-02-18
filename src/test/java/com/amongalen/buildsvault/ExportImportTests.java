package com.amongalen.buildsvault;

import com.amongalen.buildsvault.exporter.Exporter;
import com.amongalen.buildsvault.exporter.ExporterImpl;
import com.amongalen.buildsvault.importer.Importer;
import com.amongalen.buildsvault.importer.ImporterImpl;
import com.amongalen.buildsvault.model.build.PathOfBuilding;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class ExportImportTests {
    private static final Pattern TREE_LINK_PREFIX = Pattern.compile(".*passive-skill-tree/");

    @Test
    public void testExportImport() {
        String raw = "eNq9XFtz27iSfh79Cparztae8tgmCF7AbHJOybZsaxLHF9nJZF5SIABajClSISnZytb-922ApERKpEx5kpPUZGyyG2j09WsA0tt_P09CbS6SNIijd3voUN_TRMRiHkQP7_bu784OyN6__9V7e02z8ZV_PAtC-eZfvd_eqp-1UMxFCHw68GU0eRDZp3Is_BWeeTTiQfYxTiYUyD7GkSif1X-7FAkLQpGm5WMW0jT9SCfi3d6dmExDmuxpNGUi4ierN8Po-yxIgyyGlxMaRKOYPYrsPIln03d79p42D8TTZczlGLeDQTnXSTITpTCwkt_eXod0IZJRRjMthX_e7fVBIfRBXAQZjEHDGQxguMTA6NDBxLGJZe8dbeU8pRP4d1fm0VQIvmTCh6aLV39-KhOsrM5nHtpWG_F1Iga-L1gWzMVJAvob04itFueah8TUSRt3E4cLqsDEsC0L29v4LmdhFkzDQCRLXuuQWFtWtTaT9MsW4rs4o-Hp9WhFi4ntOM6hRSwDLLWdL155RusMn4NsfB0HaRy9YppLGtGTOF1NY7Yue5QllQU7bWS34nuVsl2Np-J5NZ5JtoxXo0RmG-UwqixDN7YMWKVEbuuAHwJ_ZWMTIXdLcLA3knoYscqa9G0D30eJSEUyr0bHlinqLNeQx0R1EfrWuW7Fg4hWwauTQ9ewtjF8EIKNzyHV3dJspQLILodbPWklj2E5W5UlqavKMreO26Ar1O5YdY4NVZmHDnIsgiwT43Yp5SB1tdnk0N5G3ayzVrsMIpE8LEbjQISVVTnt3qj0VuWq6s_uNE99QVucpsrU4guQXFu551TW5lUydbc7Q0FfXQ9uDYRLEQoBHFysJWH953FcJ_E3WYjC3dj6ySSeVZIksu2t687pq8tul2i8SAMGFUFV_FvBZyBeRcWt6lqW1Mt4LiYQCKogA1pZoYbDdqhwHALUWVu-oW9ZVBg2sbRrLMsoezyN-UNnJatJduI4CxLQWBpUq1wrZDmJZZjUiO0tufVhnEWAVNc4rNbcBDLH6Rr1AcIvi34FiO-ETl9e7WoBnVnWltF9qtViOvNcx09AOZYYPl1FexfqS_r8MsNZIqIfi87j18g7TTCI-CyRfrc-h96VY3Oat0eqy5E_DSfTOMnUwxMaslQNOYyms0yLVC8yCVL21Zv5vmw49mCKRLVQg7Ozwcnd8NOgkKLKkj4GYfg1mk08CXDz_8u-JaccCZVJNBaHIZ2mgr_bi4JwTwvgh5HkHEH6ZFkHasDFRUPyMq1Eth3mV83Dy3QSxHcTsMgZHZd-t5gKacm0A8Nx2E3WHKp3IBw-REHWSZeC0UUHumUh70B7CW6Wl44ua4c0ngTeLBNdiBWq7iCBxJMvk9VgVAdJ8xLdYdwCw7xMWWTzDnZSwXFHH0VXC5wKX4C7pkWWKDPCW-WcqcaFT6F1PReTmxkNg2xRsK-ef8j3TdTTdBw_jWZTmV_gjXTtFPTx4QO8yR-lxwvoIN_tZcms2LFQ86gtj77CEOp3JQWgG7mDQ71QCq04tDSMZTsq6BSWZOyt80kWOepvb2Gugvc8jD0aGuUI38tlGHiv3PMBuKE9iMlQqkRklNOMHg0zkPZIinykhoafzhLoYo_jiQdyyEeSvvKsOh0qp1uTX2ZLicsKRk1xHnUWWV-JjLaLvDSCwjInNGVU5fJS7oKg_na3FSherWT-FYso9C5TrwSmYPbHSKRpZRn9NKVpGkRQ8ZLHXRdQMv8XeFP6P6mmxsijJp_71Q56IcKJyP6ee-q7uucoi5OJF9I0uwyiqq2X2GvwPA1jmXJygh3NvRxeU-ydDb5aSDdzXyeCBXlzt7IzYwBw2KIfcekNaR9-23UBq3Ff46t2J9lvoU5CfwJYjFfddBQ8BGH-blep1VhawfsKnXd0ns8JzcYViYvfd5M1Z_opEXQcxwAL_k4A7ex2kA5pWFFB-WBHL8u5XhMddseUDik3u4pORd1ixfvay91El6wa1NWc-ejXZaoLkdCQX_lDVk1Ttae7SZ6zarGvDZn4tfW02s5dRSPoxCCxQjH_ISLAiZPFpkVe5thtsbBE7XgF2_9moBVYCv2Hi9VZCOs5pWnVgyvPdsRSklFTnL_Qa0eT-FGsldbKsx2rqWR8dSHtmin6CaORGM2ShwbsV3u5I3JSrFrO-1PcEGaei_Q_5YSrVDqWvepmEs0f75g-x8u-d8cS3TX3XEVpSGcA5pZpZDSW58S8JfO8RL9j3omgD57TbJcEi3fF-leJd-UrqFnFT7Wnu0kNrLIuFMxHv9KZKjuJV5HaL9qwSAPJrvACBtDyEbSrSCt7o5-FuvhCK48YflIoou7Ni8K6FaWdxBGfBZlCz69qV7R8xF8KCIYRSwRNBS9NMcqS4FGkm8Zvp9w1EItxltbXypFes1LctZhwLviyl8x3mq7DWcNC2yl3LDNPciML1qkG1JYjatU94B1Xi0jX0hBH8lAXWhrIl2qbsqFONNDsWDQqI2jFEL80SW0cu26uqoFkt0XlA2g57y9djTqlFDy_GLC-jtrLXUMsnQZglNX27EZmTSFJMnlzC-R6474Z0IdQaBeQbn7XRjG8SSBPt6CaIgurLdOQenLFu8IiyduqWDVwcyO81NEgYmMaZfK49so_kwJfRRdBhnfVVCf1mG_OAz9LNT-JJ1rfgzX9rp0GdBLLrQ0I6L-hIcne2L28TkN6RUV3SfDwAHbkEKSpYIm6m_D3tFP8pHbY7xIhNJqLrIhRsSMOv2gZvKzcP3SLpdzfflA__DbOsmn65ujo6enpcArteuyL5yAUhyyeHE3lruZcHKiFHMiRjvrw57h_f9w__sEGZ8EpefqmR3pwff598ef8Kpn7799_06__nA-_WI9P518m4ZfRqWN-m9u6PXs_vvRvbkY_Th_Hn0bOnH87pbNB8mmYJJdnN2NyMr778OcP9-FxdvP-IIi-nv81tU8mTzcX0_5nngRfyPXkfqF_cc_Z2YdvNGb9wHwix7fz4Z_---jg8uKHfWDf_fGs3998Zp-95-jLj-fp95tn-pV8vJ9H9FOU3v316fnh6-DDH4tPw2F0h6h3OjD7Tx9u3ctUnzzf_OU9B9-OPSsy5ufH3o_jZ3vkpV95f0pPDefb-2T-1dbnEb83Dc-bvE8vvlwa1zdeFI7s8Qc9vB389Rf_lt5dzc8o__7NO5ucXTr-xz_IwZ1_c7EQp-8fsrEZxsntPTcGvpjED_1p8Dzm2fx7RvjBuTdcRCf9d--UgY5KC73Nb3CmubmK37Qo5kK6luPa4GnQxKscV5zrbpJhw7FxBzqDmI7Vhc5Grt1lXtvGZhc67BK3y7zIJV3GM21CjA50NjJRZV7ktBFaJjKcLgMauIs9LAu5XehMeTu2i15MF3VaLqmaA9mt8yKjk7_YuoOtTva1cRcBsWmSLvNiZHeiM2zHaJAPsucqqOAXSJQqp8pMKn_4GGdCvZMPy19Unv0UiCctFTRhY4DKspj8iOPJF5X65aHlhaDZJZ0WhUG--7AEx-pQM6PZaQDwLFGHpWWil4R_lvK9VQilyOjy55HIVM2dpWIkGNS6fK9NPfZpmBYnoJJUHccW_dUtBVS_eKPd9m8HvVE8C9VuX-9mRpPsh_YZEEbvPgq-z4Q2PH2jUc93MKLMcg0HcWwQblm6Q7Fh-h7VqeuZuut6zDa4bmFmeZZBmWMJYTvEF67Le2p6tdo3mmP3imPeN5reK5T9Rjs-gL89RXMrvgOZ0RtOpmHAAvkW9QzjH1qwbEryc8EcovccvfUVgPpUw1oWawjjDXAvnyvytLdvavJAX3uAEg-DxJH2HgpbD-Hq0Kv7dJq60tGTtxA06x-y-f8YRwfqIlE5Nk018QygW6s-7f0vlHgfcPj_FYVfo1qugqXkT2MRaYt4pt2nQr6VBVa5olRi3ZhG3Zj3H4c394PeSRI_RVKkbAzmi55oAqh_IXog_XQMAaDe18xrO4wYTAiEqelbDvNNy7EM7HvYtTjiBvWF5Rg-JsjUPZ1avutbpqs7HteRg-2aeUnFvEbNvudrFrZx1cJ6D-OasvMtAk2eTBVYP-8WekbN3BP6HExmE2W93zWJwxVLDp7WOO-gU0_94GGWo261ZwO-3_Rc7lI0Pb8MIDSarYEbQusEugoWLtKJdh7Lj3v0rqLFs9afzEL4uWIC4ZmewTzbQ9TXdYQMw9QZwgQjD0zi2KB-g2Db54g4JmXCRQbCrmDYMj3HJPUII1ZVy1ZVy0ZvH9nS7Sl42uq6y8ot-2EYMwChqQbpKslSIR5F0tvH9j8kl7zHp-UXReT9IxhMPlWTSeXIQYdRBl4cPMgcljuvJvupJtJVOFbolpLsWypuq_ZtUbzZGAbr3UCv2gxUdW9hwXUdDMANE3FL6AjU67mE2pZOfWb43GHccCDPEYs6Bke-QXwX2QYhDB4a9exW1T34f033uOa5hYuu7a9o-dWulR6-yCiADlXEiXYnd-ZSjUMkg5drlPNAOiYw5wS9Mqvkeka6tmorVomFC2Cg6xP3sNVdunpeVIbUbikPZmnPqi0yN4o6NVOV6xREn8JzbyFjcxKAo0nbw5OnIBs3SFUfrsitT2OZgyEiV8sDiZN4BsG5D8vIpZTuo67xavn93C7czR5mNSda6cRFnj1OYvhfMo5jSDNP8cEoiyOx4WqmcDDXDcfCjsPBxywG6IyaruNTiwsDQ0xzzxQE6a5hcYh0w3ctRA2HENvwbace5uYWV4MwVxErr9GqhLgKtlX8pq_0sn3DkmNXQ33Nt9crbQ-b1dcqTatb_KJIrfJSfs8kqpRulGmw01wk0krliopRldSpquDamM4FeDyIwZbWV0cRGpVFPc20_Dy09LsWU9sNWfwijuRExzP2GIreKFs8BKCXT0EqamXUsG0mdI4sSByA4j1dcI84vsNdYtuQRHSbUo8TnRMCVdb1MaPIcF3bh8aAcNIbjekUYleKsl5Sl2W0X03teh0iXQDkQJDsFmkq40ex9PYdayON7kswVaqyks6RWzXSWUjTxxwV3QK0BAssNBkrPVSzpTrRV3BI4zN5ixd8ZlEw55uNqmKvjCKtoS2PjVZpaabwjmJc-aV04_Xwb7Gc02C503gSRLmHAUSneWzexRLiqdsf2n_nMOOoBhT-WTWrg5khkI0gOh3PY8ihrglo1_MFd7FgrglVmjLbFogRmxjEo7bnAg9zTI6J69VNidvREfxtxb8yoI2dAnoZXRAUh1YRV2BK6WGp6hS0wFd6h2AX2kWQSSuLKAsXPbMWy8UV0gYktm-gRljgbJh7EM7kdpFKumsJHmI4j907GoRPwUou9VDWqmyzLKxkXS7YqGeY4mMiBVBv9hjSiNiSRK5WHn9NeqcCIEN5d-8iTmq42QMkYHOwMuRm36aMCBNR3RMGsRyHWT5jpu35lJgAnxngOU4QgDkfY4kskMHXs3lzW3Reb4vWYr5etGu9j8qF2_oi5Er7YJLbr2ym6sHdWP41H6pE2Tjh3C9b8MLqM6kVjCknd1RTprvbmrJmq7kNVlvtjPfWNsarBvMxEsQBswC0dqAIQ6YmHgA6wWyA3PAfJ74BZdn2PeFx1zRt32SGBX-EYzhcX0fZjaGs2pytrexGD3gKIEQIGaJqk1xGCcRjD9da3rXQM63GFL5vOup5U3boncygXEu4nUceqqXle3X0LpawTNrYLFJFWoq0Que22xGdI71ty-EkjOlj7xMFb4FsBaqkVWMx4iGIF-pT5hNKZAGFOgqNp6lDkLkQXo4lsGWbwrMgJ3uWYXg2xBjWuW-apL2cQlysLIcrlvt8kP89PvhctZ5db1PXGpll954fDNRaHgs1lN7mlIk2SyTUQnWrqzyJXzeB2nyQfLItrcwhdx4GauehfFp3nRYzre0MXfbPhye9wTOoMJCJVCRlGhwFIWCBosKraw90FrHxGtTFxLMt16WeQz0PulYXWT50V8igwqO6hRAGyMsZhc5LZ4RavuUhC5AwtwizdIPVTGaTWqxVYK9RN85wMplFsuFQ0B8yv3T3ApbkAoscktxCYofssCIC7QIA6eEaBDotOv82nRlddXYzC9hjuqa4Pgf7wqqiGozUXc5dwyc25hb2TYR02xOCWh7hUENcZluehUBHjuMZyDKIKXwbelJuM2pZzMX1JIVaFIfWvNpw2qtnowIx2UVRuLGB6mc_giQodXQNrbqE1P2JyMYLidcVFKzuknDfcR2osi7AZtcxfWRCfvZc15IaM7jrMY-bREDxxS6oSYf6q-u66WIg8b21fUijTTVkTTUqwmp7e-rUGZYNkaYirklBeXg6irn82Gtlb3CwsTdYjlLjz5HbIFTm2G2AFlOYXX223C9Z-utnmvC1KHeQTYhpYW7YDnV826QEmUQHZTs6eK4Bke76iPmm40JC0DHyCBLQGdnIEhQ8tu6sbRYxnIYoV4nyZJakEMXbIrwgKeK7Dm1fctvmvv84zjK5Y3FGg2zcG83C6XiWbHorcYVjM-gcBAUv1YUroGcAxEEsE_pChrHjQ0dhYIaEziyOHe4Zls50Rn1GDbQGD_UW3WCrDi5OYGly-25zb0Oq4F6mONyoggYGpsZSmzXZOEgL_Y7B-QC5TEO1vaP2fPbRoZvjjhbAWLbgJbho3HwpzVh0jVsEqhFqdCoBZqpq4cZWUSav90jJtrf9qKnvPxZjcKJsDKuSTQSsbZKC5H-IJxFWLQ0QEltMd3XMkSnTk2vrPjYMQrDNhCOxJpQ0x4XGUOiEMM4N6Bm5D0jFB-DC13cQ63iDNOzXkoau2G7bFgfzkEbjrMB5sSd2Ci6mfZYoQcZ5i6aa-uxBlCXxdKEN0lTtBn2CvM7lJsmmrlxIEb5lEV0XuuBEJx70zUynjFnwk-NzzxFENwHtQcMNxRA5mPuYCBsKomuRrbpyWlWAjPrZgqCl2vaR8xOV09Sc9CMWyGIqWyTRu1hMoxhmkmcym-qxhc8sCi2IhWyLE8e2fYJBIYhxIRCnpo1Ba6bhEhfSqyFcgAS6b7gcuw72uNO-GV3fLdLzFtCQKzfR1pMxvLmB5LRsAOWde15AujTvNSWOwrj8qHd5Jak8nC2_FqJCsvpMQUlEqkT5CWn1soy6wg_pq3I2jKzNcfOzmRUN7jZqRQ5kdGOpHD4jvCnIsQDd1TfzarO43WapXHVYfuXQphqrVA2iVK_sLun0bgJULxU0jK3uVlWmt5qmV5_TKimclkEqymlYaPlxyZLEaNb4iqD8YoHilH3LCXv1YL2DF3dz40YrjZ7odN0nGu6yvORGrSw7RU2bi-woYEvAvSbiOusM7a6Ahmh9Tbi-Jl4b_HhHHZe60KQyOpGizip7IY90TySvySSdRO-qnFexoQ46bclxm0luRxHyBLNrQmiPVPSa6V5gavZX9DqToJeW1lQoGq22U1bYWmQ6D290UdRGgXrZSdDLBWCzqh0VZU3dbVNH8-q7euLIDx42vnkHSt_aFxJ5cRwKGhVRuvldPbJOqkNj9Smwk7E64n-ZTUjqYXocqy9gKL4TaHTRvx7cbpvkKqr0inmr-PJcnvzYjroYMBb8Li4-rvYSF6ii_tVPu2mi3G7sNFHlAwK7T3c2C8P6d_PtJmn-tT07s73KFMshZKNQNgdNbG-PSg99e7T-Hbv_D-j68r8=";

        Importer pobImporter = new ImporterImpl();
        PathOfBuilding build = pobImporter.importBuild(raw);
        Exporter pobExporter = new ExporterImpl();
        String pobString = pobExporter.exportBuild(build);
    }

    @Test
    public void testSkillTreeParsing() {
        String link = "https://www.pathofexile.com/passive-skill-tree/AAAABAYBABzcz3qiABa_qM_Zhgj0UXQ8BWHrjxrnVI19elM6WLzqakPvfEqLN9T_3hCSd-Xw1aKjbAtFR2yMtDi5fFFHwGZtbAQHshkmlVXGOkIPxCL0LJww-O-I-JfwHyT91CNTUu_rF7DVpvv1lPFNkh9BBLM9XyoLVUuMNkyztUgj9jB8SbFJUQce7T-D292oYeLXz5UuuMq-iujWmyZFnX_Gl_R8g5eVgptwbm0ZUDCkBa7_Oti8b42_MtGMCxEtidM2PZBVMgEgbnBSEZZLeI7pGjidqjBxMjKa4JhT-TfQH-d0O3wFtQ5IXfJSU-UZ7LChLycvBuemmWKs6-6TJw==";
        String raw = TREE_LINK_PREFIX.matcher(link).replaceAll("");
        Importer pobImporter = new ImporterImpl();
        pobImporter.extractTreeNodes(link);
    }
}
